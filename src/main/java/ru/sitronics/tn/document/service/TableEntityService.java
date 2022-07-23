package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.dto.S3FileDto;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;
import ru.sitronics.tn.document.model.InfoSupplierChainTableEntity;
import ru.sitronics.tn.document.model.NciDocumentType;
import ru.sitronics.tn.document.model.TableEntityAttachment;
import ru.sitronics.tn.document.model.TableEntityFactory;
import ru.sitronics.tn.document.model.base.BaseTableEntity;
import ru.sitronics.tn.document.repository.DocumentRepository;
import ru.sitronics.tn.document.repository.TableEntityAttachmentRepository;
import ru.sitronics.tn.document.repository.TableEntityRepository;
import ru.sitronics.tn.document.util.ObjectUtils;
import ru.sitronics.tn.document.util.S3RestServiceClient;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TableEntityService {
    private final TableEntityRepository tableEntityRepo;
    private final DocumentRepository documentRepo;
    private final TableEntityAttachmentRepository tableEntityAttachmentRepo;
    private final S3RestServiceClient s3RestServiceClient;


    @Transactional
    public BaseTableEntity create(String docId, BaseTableEntityDto tableEntityDto) {
        if (docId == null || docId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given invalid id");
        }
        if (!documentRepo.existsById(docId)) {
            throw new EntityNotFoundException(String.format("Document with id: %s doesn't exist.", docId));
        }
        String docType = documentRepo.getDocumentTypeById(docId);
        if (!docType.equals(tableEntityDto.getDocumentType().name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Types doesn't match between given tableEntity and document with id: %s. "
                                    + "Document type: %s. TableEntity type: %s"
                            , docId, docType, tableEntityDto.getDocumentType().name()));
        }
        BaseTableEntity baseTableEntity = ObjectUtils.convertObject(tableEntityDto,
                TableEntityFactory.getTableEntity(tableEntityDto.getDocumentType()));
        baseTableEntity.setDocumentId(docId);

        if (baseTableEntity instanceof InfoSupplierChainTableEntity) {
            setInfoSupplierChainTableEntityFields((InfoSupplierChainTableEntity) baseTableEntity);
        }
        return tableEntityRepo.save(baseTableEntity);
    }

    @Transactional
    public BaseTableEntity update(UUID tableEntityId, BaseTableEntityDto tableEntityDto) {
        if (tableEntityId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given docTableEntityId is null");
        }
        BaseTableEntity entityFromDb = tableEntityRepo
                .findById(tableEntityId).orElseThrow(() -> new EntityNotFoundException(String
                        .format("docTableEntity with id: %s doesn't exist.", tableEntityId)));

        if (!entityFromDb.getTableEntityType().equals(tableEntityDto.getDocumentType().name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Given wrong documentType in tableEntityDto for tableEntity with id: %s. " +
                            "Right type: %s", tableEntityId, entityFromDb.getTableEntityType()));
        }
        return tableEntityRepo.save(ObjectUtils.partialUpdate(entityFromDb,
                ObjectUtils.convertObject(tableEntityDto,
                        TableEntityFactory.getTableEntity(tableEntityDto.getDocumentType()))));
    }

    @Transactional
    public void delete(UUID tableEntityId) {
        if (tableEntityId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given docTableEntityId is null");
        }
        tableEntityRepo.deleteById(tableEntityId);
    }

    @Transactional
    public ResponseEntity<?> addAttachments(UUID tableEntityId, MultipartFile[] files, String username) {
        if (tableEntityId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid tableEntityId.");
        }
        if (!tableEntityRepo.existsById(tableEntityId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("TableEntity with id %s doesn't exist.", tableEntityId));
        }
        if (!tableEntityRepo.getTableEntityTypeById(tableEntityId).equals(NciDocumentType.
                NciDocumentTypeEnum.PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Attachment only can be added to table entity with type: %s.",
                            NciDocumentType.NciDocumentTypeEnum.
                                    PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR.name()));
        }

        Map<String, Object> response = new HashMap<>();
        List<String> failedFileNames = new ArrayList<>();
        List<TableEntityAttachment> attachments = new ArrayList<>();

        List<S3FileDto> s3FileDtoList = s3RestServiceClient.postMultipartFiles(files, username, S3FileDto.class);
        if (s3FileDtoList == null || s3FileDtoList.isEmpty()) {
            log.warn("Returned s3FileDtoList from s3-rest-service is null for tableEntity with id: {}.", tableEntityId);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Returned s3FileDtoList from s3-rest-service is null.");
        }
        s3FileDtoList.forEach(dto -> {
            if (dto.getError() == null || dto.getError().isBlank()) {
                attachments.add(tableEntityAttachmentRepo.save(new TableEntityAttachment(dto.getFileName(), dto.getId(),
                        username, tableEntityId)));
            } else {
                log.warn("File {} doesn't uploaded for tableEntity with id {}. Error from response: {}",
                        dto.getFileName(), tableEntityId, dto.getError());
                failedFileNames.add(dto.getFileName());
            }
        });
        response.put("notUploaded", failedFileNames);
        response.put("uploaded", attachments);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public void deleteAttachment(String attachId) {
        if (attachId == null || attachId.isBlank()) {
            log.warn("Given invalid comment's attachment's id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid comment's attachId.");
        }
        tableEntityAttachmentRepo.deleteById(attachId);
    }


    private void setInfoSupplierChainTableEntityFields(InfoSupplierChainTableEntity tableEntity) {
        Long maxNumberInOrder = tableEntityRepo
                .findMaxNumberInOrderByDocId(tableEntity.getDocumentId()).orElse(0L);
        tableEntity.setNumberInOrder(++maxNumberInOrder);
    }
}

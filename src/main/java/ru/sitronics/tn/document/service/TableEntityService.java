package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;
import ru.sitronics.tn.document.model.InfoSupplierChainTableEntity;
import ru.sitronics.tn.document.model.TableEntityFactory;
import ru.sitronics.tn.document.model.base.BaseTableEntity;
import ru.sitronics.tn.document.repository.DocumentRepository;
import ru.sitronics.tn.document.repository.TableEntityRepository;
import ru.sitronics.tn.document.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TableEntityService {
    private final TableEntityRepository tableEntityRepo;
    private final DocumentRepository documentRepo;


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

    public void delete(UUID tableEntityId) {
        if (tableEntityId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given docTableEntityId is null");
        }
        tableEntityRepo.deleteById(tableEntityId);
    }

    private void setInfoSupplierChainTableEntityFields(InfoSupplierChainTableEntity tableEntity) {
        Long maxNumberInOrder = tableEntityRepo
                .findMaxNumberInOrderByDocId(tableEntity.getDocumentId()).orElse(0L);
        tableEntity.setNumberInOrder(++maxNumberInOrder);
    }
}

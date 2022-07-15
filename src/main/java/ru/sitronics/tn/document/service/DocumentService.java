package ru.sitronics.tn.document.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewModule;
import com.monitorjbl.json.Match;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.dto.S3FileDto;
import ru.sitronics.tn.document.model.*;
import ru.sitronics.tn.document.repository.DocumentAttachmentRepository;
import ru.sitronics.tn.document.repository.DocumentRepository;
import ru.sitronics.tn.document.util.S3RestServiceClient;
import ru.sitronics.tn.document.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {
    @Autowired
    EntityManager manager;

    @Value("${rsql.defaultSort}")
    private String defaultSort;

    @Value("${rsql.defaultPageSize}")
    private Integer defaultPageSize;

    @Value("${rsql.defaultDeleted}")
    private String defaultDeleted;

    private final DocumentRepository repository;
    private final DocumentAttachmentRepository documentAttachmentRepo;
    private final S3RestServiceClient s3RestServiceClient;

    public Document get(String id) {
        Optional<Document> document = repository.findByIdAndDeleted(id, false);
        return document.orElseThrow(() -> new NotFoundException("Document not found: id = " + id));
    }

    public List<Document> getAll() {
        return repository.findAll();
    }

    public Document createOrUpdate(Document document) {
        String id = repository.save(document).getId();
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "returned id from DB is null");
        }
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't found doc with id " + id));
    }

    @Transactional
    public void delete(String id) {
        repository.markDocumentAsDeletedById(id);
    }


    public Map<String, Object> findAll(String filter, Integer page, Integer size, String sort) {
        Map<String, Object> responseEntity = new HashMap<>();
        StringBuilder filterBuilder = new StringBuilder(defaultDeleted);


        //pages start from 1 for user
        if (page == null || page < 1) {
            log.warn("Invalid page value (page = {}). Set default page value = 0", page);
            page = 0; //default page
        } else --page;

        if (sort == null || sort.isBlank()) {
            log.warn("Invalid sort value (sort = {}). Set default sort value = {}", sort, defaultSort);
            sort = defaultSort;    //default sort
        }
        if (size == null || size <= 0) {
            log.warn("Invalid size value (size = {}). Set default size  = {}", size, defaultPageSize);
            size = defaultPageSize;  //default size
        }

        responseEntity.put("filter", filter);
        responseEntity.put("sort", sort);
        responseEntity.put("page", page + 1);
        responseEntity.put("elementsOnPage", size);

        if (filter != null && !filter.isBlank()) {
            if ("deleted=='true'".equals(filter)) {
                filterBuilder.delete(0, defaultDeleted.length()).append(filter);
            } else {
                filterBuilder.append(";").append(filter);
            }
        }

        try {
            Specification<?> specification = RSQLJPASupport.toSpecification(filterBuilder.toString()).and(RSQLJPASupport.toSort(sort));
            @SuppressWarnings("unchecked")
            Page<Document> documentPage = repository.findAll((Specification<Document>) specification, PageRequest.of(page, size));

            responseEntity.put("totalAmount", documentPage.getTotalElements());
            responseEntity.put("pages", documentPage.getTotalPages());
            responseEntity.put("entity", documentPage.stream().toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseEntity;
    }

    public Map<String, Object> findAllFields(String filter, Integer page, Integer size, String sort, String fields) {
        String[] selectedFields = fields.split(", ");

        Map<String, List<String>> nameClassesWithSelectedFields = Arrays.stream(selectedFields)
                .filter(field -> Arrays.stream(NciDocumentType.NciDocumentTypeEnum.values())
                        .map(en -> en.name().toLowerCase()).toList()
                        .contains(field.split("\\.")[0]))
                .collect(Collectors.groupingBy(field -> field.split("\\.")[0],
                        mapping(field -> field.replaceFirst(field.split("\\.")[0] + "\\.", ""), toList())));

        Map<String, Object> response = findAll(filter, page, size, sort);
        List<Object> entities = new ArrayList<>();

        if (response.get("entity") instanceof List<?> list) {
            entities = list.stream().map(obj -> {
                if (obj instanceof Document doc) {
                    return doc;
                }
                return null;
            }).filter(Objects::nonNull).collect(toList());
        }

        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTimeSerializer.INSTANCE);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonViewModule());
        mapper.registerModule(module);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        try {
            // https://github.com/monitorjbl/json-view
            StringBuilder json = new StringBuilder();
            List<Document> entitiesList = new ArrayList<>(entities.stream()
                    .map(Document.class::cast)
                    .toList());  //?
            List<String> entityTypes = entitiesList.stream().map(Document::getType).toList();
            Optional<Integer> optionalInteger;
            int index = -1;

            try {
                while (entitiesList.size() > 0) {
                    json.append(mapper.writeValueAsString((JsonView.with(entitiesList.remove(0))
                            .onClass(Document.class, Match.match().exclude("*").include(selectedFields))
                    ))).append(",");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            json = new StringBuilder("[" + json.toString().replaceFirst("},$", "}]\""));
            JsonNode node = mapper.readTree(json.toString());
            response.put("entity", node);

            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public ResponseEntity<?> addAttachmentsToDocument(String documentId, MultipartFile[] files, String username) {
        if (documentId == null || documentId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid documentId.");
        }
        if (!repository.existsById(documentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Document with id %s doesn't exist.", documentId));
        }
        Document documentFromDb = repository.getById(documentId);
        Map<String, Object> response = new HashMap<>();
        List<String> failedFileNames = new ArrayList<>();
        List<DocumentAttachment> attachments = new ArrayList<>();

        List<S3FileDto> s3FileDtoList = s3RestServiceClient.postMultipartFiles(files, username, S3FileDto.class);
        if (s3FileDtoList == null || s3FileDtoList.isEmpty()) {
            log.warn("Returned s3FileDtoList from s3-rest-service is null for doc id: {}.", documentId);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Returned s3FileDtoList from s3-rest-service is null.");
        }

        s3FileDtoList.forEach(dto -> {
            if (dto.getError() == null || dto.getError().isBlank()) {
                attachments.add(documentAttachmentRepo
                        .save(new DocumentAttachment(dto.getFileName(), dto.getId(), username, documentFromDb)));
            } else {
                log.warn("File {} doesn't uploaded for document with id {}. Error from response: {}",
                        dto.getFileName(), documentId, dto.getError());
                failedFileNames.add(dto.getFileName());
            }
        });
        response.put("notUploaded", failedFileNames);
        response.put("uploaded", attachments);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public void deleteDocAttachment(String attachId) {
        if (attachId == null || attachId.isBlank()) {
            log.warn("Given invalid comment's attachment's id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid comment's attachId.");
        }
        documentAttachmentRepo.deleteById(attachId);
    }

    public List<Document> getDocumentsById(List<String> listId) {

        var listContainsNull = listId.stream().anyMatch(Objects::isNull);
        if (listContainsNull || listId == null)
            throw new RuntimeException("A list cannot contain null or be null.");

        var documents = repository.findAllById(listId);
        log.debug(documents.toString());

        if (documents.isEmpty()) throw new NotFoundException("Documents with such IDs were not found.");

        var documentsCount = documents.stream().count();
        var listIdCount = listId.stream().count();
        if (documentsCount != listIdCount)
            log.warn("The number of id documents transmitted does not match the number of documents found.");

        return documents;
    }

    ;
}





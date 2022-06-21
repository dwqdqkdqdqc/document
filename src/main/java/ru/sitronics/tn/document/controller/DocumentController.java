package ru.sitronics.tn.document.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.dto.DocumentPageDto;
import ru.sitronics.tn.document.model.*;
import ru.sitronics.tn.document.service.DocumentService;
import ru.sitronics.tn.document.service.NciDocumentTypeService;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Tag(name = "Document controller")
@RestController
@RequestMapping(value = DocumentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    static final String REST_URL = "/documents";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DocumentService service;
    @Autowired
    private NciDocumentTypeService documentTypeService;

    @GetMapping("/{id}")
    public Document get(@PathVariable String id) {
        log.info("get document {}", id);
        return service.get(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> create(@RequestBody Document document) {
        log.info("creating document: {}", document.toString());
        return new ResponseEntity<>(service.createOrUpdate(document), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.info("delete document {} ", id);
        service.delete(id);
    }

    @Operation(summary = "Get all documents")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "filter", required = false) String filter,
                                                  @RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "size", required = false) Integer size,
                                                  @RequestParam(value = "sort", required = false) String sort,
                                                  @RequestParam(value = "fields", required = false) String fields) {
        try {
            if (fields == null || fields.isBlank()) {
                return new ResponseEntity<>(service.findAll(filter, page, size, sort), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(service.findAllFields(filter, page, size, sort, fields), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }
     //   return ResponseEntity.ok(service.getDocuments(filter, page, size, sort, fields));
    }

    @PatchMapping("/{id}")
    public Document updateDocument(@PathVariable String id, @RequestBody Document document) {
        Document currentDocument = service.get(id);
        document = (Document) PersistenceUtils.partialUpdate(currentDocument, document);
        return service.createOrUpdate(document);

    }

    public static class PersistenceUtils {
        public static Object partialUpdate(Object dbObject, Object partialUpdateObject) {
            String[] ignoredProperties = getNullPropertyNames(partialUpdateObject);
            BeanUtils.copyProperties(partialUpdateObject, dbObject, ignoredProperties);
            return dbObject;
        }

        private static String[] getNullPropertyNames(Object object) {
            final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
            return Stream.of(wrappedSource.getPropertyDescriptors())
                    .map(FeatureDescriptor::getName)
                    .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                    .toArray(String[]::new);
        }
    }

    @GetMapping("/types")
    public List<String> getDocumentTypes() {
        return Stream.of(NciDocumentType.NciDocumentTypeEnum.values())
                .map(NciDocumentType.NciDocumentTypeEnum::name).toList();
    }

    @GetMapping("/customers")
    public List<String> getCustomers() {
        return Stream.of(NciCustomer.values()).map(NciCustomer::name).toList();
    }

    @GetMapping("/statuses")
    public List<String> getStatuses() {
        return Stream.of(NciStatus.values()).map(NciStatus::name).toList();
    }

    @GetMapping("/accessLimitations")
    public List<String> getAccessLimitations() {
        return Stream.of(NciAccessLimitation.values()).map(NciAccessLimitation::name).toList();
    }

    @GetMapping("/typesWithTranslate")
    public Map<NciDocumentType.NciDocumentTypeEnum, String> getDocumentTypesWithTranslate() {
        Map<NciDocumentType.NciDocumentTypeEnum, String> map = new EnumMap<>(NciDocumentType.NciDocumentTypeEnum.class);
        Arrays.asList(NciDocumentType.NciDocumentTypeEnum.values()).forEach(value -> map.put(value, value.getTranslate()));
        return map;
    }

    @GetMapping("/customersWithTranslate")
    public Map<NciCustomer, String> getCustomersWithTranslate() {
        Map<NciCustomer, String> map = new EnumMap<>(NciCustomer.class);
        Arrays.asList(NciCustomer.values()).forEach(value -> map.put(value, value.getTranslate()));
        return map;
    }

    @PostMapping(value = "/{id}/comments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addComment(@PathVariable("id") String docId,
                                        Comment comment,
                                        @RequestPart(required = false) MultipartFile[] files) {

        if (files == null) {
            return new ResponseEntity<>(service.addComment(docId, comment), HttpStatus.CREATED);
        } else return service.addCommentWithAttachment(docId, comment, files);
    }

    @PostMapping(value = "/comments/{id}/attachments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAttachment(@PathVariable("id") String commentId,
                                           @RequestPart MultipartFile[] files) {
        return service.addAttachmentsToComment(commentId, files);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") String commentId) {
        service.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/comments/attachments/{id}")
    public ResponseEntity<?> deleteCommentAttachment(@PathVariable("id") String attachId) {
        service.deleteCommentAttachment(attachId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

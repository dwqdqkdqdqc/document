package ru.sitronics.tn.document.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.model.*;
import ru.sitronics.tn.document.service.DocumentService;
import ru.sitronics.tn.document.util.exception.NotFoundException;
import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Tag(name = "Document controller")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = DocumentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    static final String REST_URL = "/documents";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DocumentService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        log.info("get document {}", id);
        try {
            return ResponseEntity.ok(service.get(id));
        } catch(NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> create(@RequestBody Document document) {
        log.info("creating document: {}", document.toString());
        return new ResponseEntity<>(service.createOrUpdate(document), HttpStatus.CREATED);
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
    }

    @PatchMapping("/{id}")
    public Document updateDocument(@PathVariable String id, @RequestBody Document document) {
        Document currentDocument = service.get(id);
        document = (Document) PersistenceUtils.partialUpdate(currentDocument, document);
        return service.createOrUpdate(document);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
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

    @GetMapping("/contractors")
    public List<String> getContractors() {
        return Stream.of(NciContractor.values()).map(NciContractor::name).toList();
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

    @GetMapping("/contractorsWithTranslate")
    public Map<NciContractor, String> getContractorsWithTranslate() {
        Map<NciContractor, String> map = new EnumMap<>(NciContractor.class);
        Arrays.asList(NciContractor.values()).forEach(value -> map.put(value, value.getTranslate()));
        return map;
    }
}
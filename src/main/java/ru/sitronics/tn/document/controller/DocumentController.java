package ru.sitronics.tn.document.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.service.DocumentService;
import ru.sitronics.tn.rsql.CustomRsqlVisitor;

import java.beans.FeatureDescriptor;
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

    @GetMapping("/{id}")
    public Document get(@PathVariable String id) {
        log.info("get {} for user ", id);
        return service.get(id);
    }

/*    @GetMapping
    public List<Document> getAll() {
        log.info("getAll documents for user ");
        return service.getAll();
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Document createOrUpdate(Document document) {
        log.info("create {} for user", document);
        return service.createOrUpdate(document);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.info("delete document {} for user", id);
        service.delete(id);
    }

    @GetMapping("/query")
    @ResponseBody
    public List<Document> getByQuery(@RequestParam(value = "where") String where) {
        log.info("completion of a selection of documents on request {}", where);
        Node rootNode = new RSQLParser().parse(where);
        Specification<Document> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return service.getByQuery(spec);
    }

    @Operation(summary = "Get all documents")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size,
                                                      @RequestParam(value = "sort", required = false) String sort) {
        try {
            return new ResponseEntity<>(service.findAll(filter, page, size, sort), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }
    }

    @GetMapping("/serialNumber")
    public List<Document> getSerialNumber(Long serialNumber) {
        return service.getSerialNumber(serialNumber);
    }

    @PatchMapping("/{id}")
    public Document updateUser(@PathVariable String id, @RequestBody Document document) {
        Document currentDocument = service.get(id);
        document = (Document) PersistenceUtils.partialUpdate(currentDocument, document);
        return service.createOrUpdate(document);

    }

    public static class PersistenceUtils {
        public static Object partialUpdate(Object dbObject, Object partialUpdateObject){
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
}

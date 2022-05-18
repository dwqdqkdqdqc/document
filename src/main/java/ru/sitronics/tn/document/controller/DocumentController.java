package ru.sitronics.tn.document.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.service.DocumentService;
import ru.sitronics.tn.document.util.exception.NotFoundException;
import ru.sitronics.tn.rsql.CustomRsqlVisitor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Tag(name = "Document controller")
@RestController
@RequestMapping(value = DocumentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    static final String REST_URL = "/documents";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private DocumentService service;

    @GetMapping("/{id}")
    public Document get(@PathVariable String id) {
        log.info("get {} for user ", id);
        return service.get(id);
    }

    @GetMapping
    public List<Document> getAll() {
        log.info("getAll documents for user ");
        return service.getAll();
    }

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
        Specification<Document> spec = rootNode.accept(new CustomRsqlVisitor<Document>());
        return service.getByQuery(spec);
    }

    @GetMapping("/serialNumber")
    public List<Document> getSerialNumber(Long serialNumber) {
        return service.getSerialNumber(serialNumber);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Document> updateDocument(@PathVariable String id, @RequestBody JsonPatch patch) {
        log.info("update document {}, patch {}", id, patch);
        try {
            Document document = service.get(id);
            Document documentPatched = applyPatchToDocument(patch, document);
            service.createOrUpdate(documentPatched);
            return ResponseEntity.ok(documentPatched);

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Document applyPatchToDocument(JsonPatch patch, Document targetDocument) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetDocument, JsonNode.class));
        return objectMapper.treeToValue(patched, Document.class);
    }

/*    @GetMapping("/serialNumber")
    public Document getBySerialNumber(long serialNumber) {
        return service.getByRsql(serialNumber);
    }*/
/*
    @GetMapping("/serialNumberContaining")
    public List<Document> getSerialNumberContaining(long serialNumber) {
        return service.getSerialNumberContaining(serialNumber);
    }

    @GetMapping("/filter/serialNumberOrderByAuthor")
    public List<Document> getBySerialNumberOrderByAuthor(long serialNumber) {
        return service.getBySerialNumberOrderByAuthor(serialNumber);
    }

    @GetMapping("/filter/serialNumberOrderByCurators")
    public List<Document> getBySerialNumberOrderByCurators(long serialNumber) {
        return service.getBySerialNumberOrderByCurators(serialNumber);
    }

    @GetMapping("/filter/dateOfCreationBetween")
    public List<Document> getByCreatDateBetween(@RequestParam @Nullable String startDate, @RequestParam @Nullable String endDate) {
        return service.getByDateOfCreationBetween(parseLocalDateTime(startDate), parseLocalDateTime(endDate));
    }

    @GetMapping("/filter/dateOfCreationGreaterThanEqual")
    public List<Document> getByCreatDateGreaterThanEqual(@RequestParam @Nullable String startDate) {
        return service.getByDateOfCreationGreaterThanEqual(parseLocalDateTime(startDate));
    }

    @GetMapping("/filter/dateOfCreationLessThanEqual")
    public List<Document> getByCreatDateLessThanEqual(@RequestParam @Nullable String startDate) {
        return service.getByDateOfCreationLessThanEqual(parseLocalDateTime(startDate));
    }*/
}

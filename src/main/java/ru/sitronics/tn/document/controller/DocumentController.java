package ru.sitronics.tn.document.controller;

import com.beust.jcommander.internal.Nullable;
import ru.sitronics.tn.document.model.BaseDocument;
import ru.sitronics.tn.document.service.DocumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static ru.sitronics.tn.document.util.DateTimeUtil.parseLocalDateTime;

@Tag(name = "Document controller")
@RestController
@RequestMapping(value = DocumentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    static final String REST_URL = "/documents";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DocumentService service;

    @GetMapping("/{id}")
    public BaseDocument get(@PathVariable String id) {
        log.info("get {} for user ", id);
        return service.get(id);
    }

    @GetMapping
    public List<BaseDocument> getAll() {
        log.info("getAll documents for user ");
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseDocument createOrUpdate(BaseDocument document) {
        log.info("create {} for user", document);
        return service.createOrUpdate(document);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        log.info("delete document {} for user", id);
        service.delete(id);
    }

    @GetMapping("/number")
    public BaseDocument getByName(long number){
        return service.getByNumber(number);
    }

    @GetMapping("/filter/serialNumberOrderByCreator")
    public List<BaseDocument> getBySerialNumberOrderByCreator(long number){
        return service.getBySerialNumberOrderByCreator(number);
    }

    @GetMapping("/filter/serialNumberOrderByCurators")
    public List<BaseDocument> getBySerialNumberOrderByCurators(long number){
        return service.getBySerialNumberOrderByCurators(number);
    }

    @GetMapping("/filter/creationDateBetween")
    public List<BaseDocument> getByCreationDateBetween(@RequestParam @Nullable String startDate, @RequestParam @Nullable String endDate){
        return service.getByCreationDateBetween(parseLocalDateTime(startDate), parseLocalDateTime(endDate));
    }

    @GetMapping("/filter/creationDateGreaterThanEqual")
    public List<BaseDocument> getByCreationDateGreaterThanEqual(@RequestParam @Nullable String startDate){
        return service.getByCreationDateGreaterThanEqual(parseLocalDateTime(startDate));
    }

    @GetMapping("/filter/creationDateLessThanEqual")
    public List<BaseDocument> getByCreationDateLessThanEqual(@RequestParam @Nullable String startDate){
        return service.getByCreationDateLessThanEqual(parseLocalDateTime(startDate));
    }
}

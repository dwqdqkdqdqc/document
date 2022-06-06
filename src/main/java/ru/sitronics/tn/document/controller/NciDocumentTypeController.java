package ru.sitronics.tn.document.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sitronics.tn.document.service.NciDocumentTypeService;

import java.util.List;

@Tag(name = "NciDocumentType controller")
@RestController
@RequestMapping(value = NciDocumentTypeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NciDocumentTypeController {
    static final String REST_URL = "/documentTypes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NciDocumentTypeService service;

    @GetMapping
    public List<String> getDocumentTypes(){
        return service.getAll();
    }
}

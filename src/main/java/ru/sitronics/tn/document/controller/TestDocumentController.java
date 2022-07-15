package ru.sitronics.tn.document.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.service.TestDocumentService;

import java.util.List;

@Tag(name = "Document controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
public class TestDocumentController {

    @Autowired
    private TestDocumentService service;

    @GetMapping("/getAllDoc")
    public ResponseEntity<List<Document>> getAllDoc() {
        return ResponseEntity.ok().body(service.findAllDocuments());


    }   @GetMapping("/getdocto")
    public ResponseEntity<List<Document>> getdocto(

    ) {
        return ResponseEntity.ok().body(service.findTo());
    }
}

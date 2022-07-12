package ru.sitronics.tn.document.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sitronics.tn.document.dto.DocumentRelationDto;
import ru.sitronics.tn.document.service.DocumentRelationService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Tag(name = "Link document controller")
@RequiredArgsConstructor
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/link")
public class DocumentRelationController {

    private final DocumentRelationService documentRelationService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping(path = "/create", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLink(@RequestBody DocumentRelationDto relationDto) {

        log.debug(relationDto.toString());
        documentRelationService.create(relationDto);

        return ResponseEntity.noContent().build();
    }
}

package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.dto.DocumentResponsibleDto;
import ru.sitronics.tn.document.model.DocumentResponsible;
import ru.sitronics.tn.document.repository.DocumentResponsibleRepository;
import ru.sitronics.tn.document.util.ObjectUtils;
import ru.sitronics.tn.document.util.exception.NotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentResponsibleService {

    private final DocumentResponsibleRepository repository;

    private final DocumentService documentService;


    public DocumentResponsible create(DocumentResponsibleDto responsibleDto) {

        var documentId = responsibleDto.getDocumentId();
        var userLogin = responsibleDto.getUserLogin();
        var typeRelationId = responsibleDto.getTypeRelation();


        if ((StringUtils.isBlank(documentId))
                || (StringUtils.isBlank(userLogin))
                || (StringUtils.isBlank(typeRelationId)))
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Parameters in the request body cannot be null.");

        var documentIdExist = documentService.existById(documentId);
        if (!documentIdExist) throw
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Documents with such IDs were not found.");

//        var responsibleByThisDocumentIdAlreadyExist =
//                repository.existsDocumentResponsibleByDocumentId(documentId);
//        if (responsibleByThisDocumentIdAlreadyExist) throw
//            new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "The responsible is already linked to the submitted document");

        var documentResponsibleEntity =
                ObjectUtils.convertObject(responsibleDto, new DocumentResponsible());


        log.debug(responsibleDto.toString());

        return repository.save(documentResponsibleEntity);
    }
}

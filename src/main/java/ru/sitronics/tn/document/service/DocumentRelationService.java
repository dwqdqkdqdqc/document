package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.dto.DocumentRelationDto;
import ru.sitronics.tn.document.model.DocumentRelation;
import ru.sitronics.tn.document.repository.DocumentRelationRepository;
import ru.sitronics.tn.document.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentRelationService {

    private final DocumentRelationRepository repository;


    public List<DocumentRelation> create(DocumentRelationDto relationDto) {

        var documentId = relationDto.getDocumentId();
        var linkDocumentId = relationDto.getLinkDocument();
        var typeLinkId = relationDto.getTypeRelation();


        if ((StringUtils.isBlank(documentId))
                || (StringUtils.isBlank(linkDocumentId))
                || (StringUtils.isBlank(typeLinkId)))
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Parameters in the request body cannot be null.");


        if (documentId.equals(linkDocumentId))
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Document IDs cannot be the same.");


        var documentRelationEntity = ObjectUtils.convertObject(relationDto, new DocumentRelation());

        var reverseDocumentRelationEntity = new DocumentRelation();
        reverseDocumentRelationEntity.setDocumentId(linkDocumentId);
        reverseDocumentRelationEntity.setLinkDocument(documentId);
        reverseDocumentRelationEntity.setTypeRelation(typeLinkId);


//        var relationEntity = repository.findById(documentId)
//                .orElseThrow(() -> new EntityNotFoundException("Can't found doc with id " + documentId));
//
//        var reverseRelationEntity = repository.findById(linkDocumentId)
//                .orElseThrow(() -> new EntityNotFoundException("Can't found doc with id " + documentId));

        return  repository.saveAll(List.of(documentRelationEntity, reverseDocumentRelationEntity));
    }


}

package ru.sitronics.tn.document.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DocumentRelationDto {

    private UUID id;

    private String documentId;

    private String linkDocument;

    private String typeRelation;

    private String author;

    public DocumentRelationDto(String documentId, String linkDocument, String typeRelation) {
        this.documentId = documentId;
        this.linkDocument = linkDocument;
        this.typeRelation = typeRelation;
    }
}

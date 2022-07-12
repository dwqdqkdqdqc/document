package ru.sitronics.tn.document.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.UUID;

@Data
public class DocumentRelationDto {

    private UUID id;

    private String documentId;

    private String linkDocument;

    private String typeRelation;

}

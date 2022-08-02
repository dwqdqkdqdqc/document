package ru.sitronics.tn.document.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class DocumentResponsibleDto {

    private UUID id;

    private String userLogin;

    private String documentId;

    private String typeRelation;

}

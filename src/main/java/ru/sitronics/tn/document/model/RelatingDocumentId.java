package ru.sitronics.tn.document.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RelatingDocumentId implements Serializable {
    @Column(name = "document_id", nullable = false, insertable = false, updatable=false)
    private String documentId;

    @Column(name = "relating_document_id", nullable = false, insertable = false, updatable=false)
    private String relatingDocumentId;

    @Column(name = "type_relation_id", nullable = false, insertable = false) //Как добавить enum TypeRelation?
    private String typeRelation;
}

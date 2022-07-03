package ru.sitronics.tn.document.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class LinkDocumentId implements Serializable {
    @Column(name = "document_id", nullable = false, insertable = false, updatable=false)
    private String documentId;

    @Column(name = "link_document_id", nullable = false, insertable = false, updatable=false)
    private String linkDocumentId;

    @Column(name = "type_link_id", nullable = false, insertable = false)
    private String typeLink;
}

package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_document_statuses")
public class NciDocumentStatus extends BaseEntity {
    @Column(name = "internal_id")
    private Integer internalId;
    @Column(name = "status_document")
    private String statusDocument;
    @Column(name = "status_document_rus")
    private String statusDocumentRus;
}

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
@Table(name = "document_history_bpm")
public class DocumentHistoryBpm extends BaseEntity {
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "document_id")
    private String documentId;
    @Column(name = "relating_document_id")
    private String relatingDocumentId;
    @Column(name = "type_relation")
    private String typeRelation;
}

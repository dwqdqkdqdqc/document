package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "documents_relating_documents")
public class RelatingDocumentsTable {
    @EmbeddedId
    RelatingDocumentId id;

    @ManyToOne
    @JsonBackReference
    @MapsId("documentId")
    @JoinColumn(name = "document_id")
    Document documentId;

    @ManyToOne
    @JsonBackReference
    @MapsId("relatingDocumentId")
    @JoinColumn(name = "relating_document_id")
    Document relatingDocument;

    @ManyToOne
    @JsonBackReference
    @MapsId("typeRelation")
    @JoinColumn(name = "type_relation_id")
    TypeRelation typeRelation;

    @Column(name = "relating_document_serial_number")
    String serialNumber;
}


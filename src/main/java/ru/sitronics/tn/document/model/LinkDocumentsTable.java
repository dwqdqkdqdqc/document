package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "documents_link_documents")
public class LinkDocumentsTable {
    @EmbeddedId
    LinkDocumentId id;

    @ManyToOne
    @JsonBackReference(value = "document_id")
    @MapsId("documentId")
    @JoinColumn(name = "document_id")
    Document documentId;

    @ManyToOne
    @JsonBackReference(value = "link_document_id")
    @MapsId("linkDocumentId")
    @JoinColumn(name = "link_document_id")
    Document linkDocument;

    @ManyToOne
    @JsonBackReference
    @MapsId("typeLink")
    @JoinColumn(name = "type_link_id")
    TypeRelation typeRelation;

    @Column(name = "link_document_serial_number")
    String serialNumber;

}


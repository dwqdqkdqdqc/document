package ru.sitronics.tn.document.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "relation_document",
        uniqueConstraints = { @UniqueConstraint(name = "DocumentUniqueRelationConstraint",
                columnNames = { "document_id", "link_document_id", "type_relation_id" }) })
public class DocumentRelation {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "link_document_id")
    private String linkDocument;

    @Column(name = "type_relation_id")
    private String typeRelation;
}

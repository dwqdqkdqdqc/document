package ru.sitronics.tn.document.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "relation_document")
public class DocumentRelation {

    @Id
    @GeneratedValue
    private String id;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "link_document_id")
    private String linkDocument;

    @Column(name = "type_relation_id")
    private String typeRelation;




}


package ru.sitronics.tn.document.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "document_responsible",
        uniqueConstraints = {@UniqueConstraint(name = "DocumentUniqueResponsibleConstraint",
                columnNames = {"document_id", "user_login", "type_relation_id"})})
public class DocumentResponsible {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "document_id")
    private String documentId;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "type_relation_id")
    private String typeRelation;
}

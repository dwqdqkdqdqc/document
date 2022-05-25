package ru.sitronics.tn.document.model;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(javax.persistence.AccessType.FIELD)
@Table(name = "nci_document_types")
public class NciDocType extends BaseEntity  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "name_rus")
    private String nameRus;

}

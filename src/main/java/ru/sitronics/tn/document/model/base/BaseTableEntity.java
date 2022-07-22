package ru.sitronics.tn.document.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "table_entity_type")
@DiscriminatorValue("BASE_ENTITY")
@Table(name = "table_entities")
public class BaseTableEntity extends BaseEntityUUID implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "doc_id")
    @JsonIgnore
    private String documentId;

    @Column(name = "table_entity_type", insertable = false, updatable = false)
    @JsonIgnore
    private String tableEntityType;

}


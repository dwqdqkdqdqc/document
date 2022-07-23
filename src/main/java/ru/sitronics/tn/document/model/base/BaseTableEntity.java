package ru.sitronics.tn.document.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.sitronics.tn.document.model.TableEntityAttachment;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


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
    @Column(name = "pid")
    private String pid;
    @Column(name = "producer")
    private String producer;
    @Column(name = "mtr_name")
    private String mtrName;
    @Column(name = "number_in_order")
    private Long numberInOrder;
    @Column(name = "mtr_group")
    private String mtrGroup;
    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @JoinColumn(name = "table_entity_id", referencedColumnName = "id")
    private List<TableEntityAttachment> tableEntityAttachments;

}


package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.sitronics.tn.document.model.base.BaseEntityUUID;
import ru.sitronics.tn.document.model.tableEntity.MtrProductionAndShipmentPlanTableEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pid_entity")
public class PidEntity extends BaseEntityUUID {

    @ManyToOne
    @JoinColumn(name = "table_entity_id")
    @JsonIgnore
    @JsonBackReference
    private MtrProductionAndShipmentPlanTableEntity mtrProductionAndShipmentPlanTableEntity;

    @Column(name = "factory_number")
    private String factoryNumber;

    @OneToMany(mappedBy = "pidEntity", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @OrderBy("orderNum")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<ProductionPhase> productionPhases;
}

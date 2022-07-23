package ru.sitronics.tn.document.model.tableEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.sitronics.tn.document.model.base.BaseTableEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("MTR_PRODUCTION_AND_SHIPMENT_PLAN")
@JsonIgnoreProperties(value = {"tableEntityAttachments"})
public class MtrProductionAndShipmentPlanTableEntity extends BaseTableEntity {
    @Column(name = "specification_date")
    private LocalDate specificationDate;
    @Column(name = "specification_number")
    private Long specificationNumber;
    @Column(name = "production_start_date")
    private LocalDate productionStartDate;
    @Column(name = "shipment_date")
    private LocalDate shipmentDate;
    @Column(name = "production_time_days")
    private Long productionTimeDays;
    @Column(name = "set_delivery")
    private boolean setDelivery;
    @Column(name = "control_prod")
    private boolean controlProd;
    @Column(name = "delivery_method")
    private String deliveryMethod;

    @OneToMany(mappedBy = "mtrProductionAndShipmentPlanTableEntity",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @JsonManagedReference
    private List<PidEntity> pidEntities;

}

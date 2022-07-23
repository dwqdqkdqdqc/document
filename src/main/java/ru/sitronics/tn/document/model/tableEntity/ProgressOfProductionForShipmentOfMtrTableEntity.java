package ru.sitronics.tn.document.model.tableEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sitronics.tn.document.model.base.BaseTableEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR")
@JsonIgnoreProperties(value = {"numberInOrder"})
public class ProgressOfProductionForShipmentOfMtrTableEntity extends BaseTableEntity {
    @Column(name = "factory_number")
    private String factoryNumber;
    @Column(name = "number_phase")
    private Long numberPhase;
    @Column(name = "phase_name")
    private String phase_name;
    @Column(name = "plan_date")
    private LocalDate planDate;
    @Column(name = "fact_date")
    private LocalDate factDate;

}

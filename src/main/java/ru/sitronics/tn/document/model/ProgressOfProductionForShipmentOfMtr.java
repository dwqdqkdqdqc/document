package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR")
public class ProgressOfProductionForShipmentOfMtr extends Document{
    @Column(name = "nci_mtr_group_id")
    private String nciMtrGroup;
    @Column(name = "nci_mtr_id")
    private String nciMtr;
    @Column(name = "phase_number")
    private String phaseNumber;
    @Column(name = "plan_date")
    private LocalDate planDate;
    @Column(name = "fact_date")
    private LocalDate factDate;
    @Column(name = "verify_document")
    private String verifyDocument;
}

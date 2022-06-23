package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MTR_PRODUCTION_AND_SHIPMENT_PLAN")
public class MtrProductionAndShipmentPlan extends Document {
/*

    private Integer positionNumber;

    @OneToOne
    @JoinColumn(name = "nci_mtr_group_id")
    private NciMtrGroup nciMtrGroup;

    @OneToOne
    @JoinColumn(name = "nci_mtr_id")
    private NciMtr nciMtr;
    @ManyToOne
    @JoinColumn(name = "nci_units_measurement_id")
    private NciUnitsMeasurement nciUnitsMeasurement;
    private Integer quantity;
    private NciCustomer nciProducer;
    private LocalDate dateSupply;
    private LocalDate dateProduction;
    private LocalDate dateShipment;
    private LocalDate productionTime;
    private LocalDate supplyTnp;

    private LocalDate planDate;
    private boolean controlProd;
    @ManyToOne
    @JoinColumn(name = "nci_delivery_method_id")
    private NciDeliveryMethod nciDeliveryMethod;
    private String barcode;    @ManyToOne
    @JoinColumn(name = "nci_object_id")
    private NciObject nciObject;
*/
}

package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MTR_PRODUCTION_AND_SHIPMENT_PLAN")
public class MtrProductionAndShipmentPlan extends Document {
    @ManyToOne
    @JoinColumn(name = "nci_type_id")
    private NciDocumentType nciType;
    private String serialNumber;
    private LocalDateTime createDate;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Document contract;
    @ManyToOne
    @JoinColumn(name = "nci_create_user_id")
    private NciUser nciCreateUser;
    @ManyToOne
    @JoinColumn(name = "nci_ost_id")
    private NciOst nciOst;
    private NciStatus nciStatus;
    private NciAccessLimitation nciAccessLimitation;
    private String comment;
    private String pidNumber;
    private Long positionNumber;
    private NciCustomer nciCustomer;
    @ManyToOne
    @JoinColumn(name = "nci_mtr_group_id")
    private NciMtrGroup nciMtrGroup;
    @ManyToOne
    @JoinColumn(name = "nci_factory_number_id")
    private NciFactoryNumber nciFactoryNumber;
    @ManyToOne
    @JoinColumn(name = "nci_mtr_id")
    private NciMtr nciMtr;
    @ManyToOne
    @JoinColumn(name = "nci_units_id")
    private NciUnits nciUnits;
    private Long quantity;
    private NciCustomer nciProducer;
    private LocalDateTime dateSupply;
    private LocalDateTime dateProduction;
    private LocalDateTime dateShipment;
    private LocalDateTime productionTime;
    private LocalDateTime supplyTnp;
    @ManyToOne
    @JoinColumn(name = "nci_phase_id")
    private NciPhase nciPhase;
    private LocalDateTime planDate;
    private boolean controlProd;
    @ManyToOne
    @JoinColumn(name = "nci_delivery_method_id")
    private NciDeliveryMethod nciDeliveryMethod;
    private String barcode;
    @ManyToOne
    @JoinColumn(name = "nci_construction_object_id")
    private NciConstructionObject nciConstructionObject;
    private String lkkNumber;
    private String lysNumber;
}

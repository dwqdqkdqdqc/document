package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "specification_table_entities")
public class SpecificationTableEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "pid")
    private String pid;

    @Column(name = "position_number")
    private Long positionNumber;

    @Column(name = "delivery_method")
    private boolean deliveryMethod;

    @Column(name = "position_code")
    private Long positionCode;

    @ManyToOne
    @JoinColumn(name = "nci_mtr_id")
    private NciMtr nciMTR;

    @Column(name = "gost_ost_tu")
    private String gostOstTu;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "nci_unit_of_measurement_id")
    private NciUnitsOfMeasurement nciUnitsOfMeasurement;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price_no_vat")
    private BigDecimal priceNoVat;

    @Column(name = "sum_no_vat")
    private BigDecimal sumNoVat;

    @Column(name = "vat")
    private Double vat;

    @Column(name = "sum_vat")
    private BigDecimal sumVat;

    @Column(name = "amount_with_vat")
    private BigDecimal amountWithVat;

    @Column(name = "contractor_id")
    private String contractor;

    @ManyToOne
    @JoinColumn(name = "nci_country_id")
    private NciCountry nciCountry;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "nci_type_of_transport_id")
    private NciTypeOfTransport nciTypeOfTransport;

    @Column(name = "belonging_to_the_dsi")
    private String belongingToTheDSI;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specification_id")
    @JsonBackReference
    private Specification specification;
}

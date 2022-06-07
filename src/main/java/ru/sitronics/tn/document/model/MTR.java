package ru.sitronics.tn.document.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mtrs")
public class MTR extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "pid")
    private String pid;

    @Column(name = "position_number")
    private Long positionNumber;

    @Column(name = "customer_id")
    private String customer;

    @ManyToOne
    @JoinColumn(name = "nci_delivery_id")
    private NciDelivery nciDelivery;

    @Column(name = "position_code")
    private Long positionCode;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "nci_unit_of_measurement_id")
    private NciUnitsOfMeasurement nciUnitsOfMeasurement;

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
    private LocalDateTime deliveryDate;

    @Column(name = "shipping_details")
    private String shippingDetails;

    @ManyToOne
    @JoinColumn(name = "nci_type_of_transport_id")
    private NciTypeOfTransport nciTypeOfTransport;

    @Column(name = "belonging_to_the_dsi")
    private String belongingToTheDSI;
}

package ru.sitronics.tn.document.model.tableEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sitronics.tn.document.model.base.BaseTableEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("SPECIFICATION")
@JsonIgnoreProperties(value = {"tableEntityAttachments", "producer", "mtrName", "numberInOrder"})
public class SpecificationTableEntity extends BaseTableEntity {
    @Column(name = "position_number")
    private Long positionNumber;
    @Column(name = "tnl_delivery")
    private boolean tnlDelivery;
    @Column(name = "position_code")
    private Long positionCode;
    @Column(name = "gost_ost_tu")
    private String gostOstTu;
    @Column(name = "code")
    private String code;
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
    @Column(name = "country")
    private String country;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "type_of_transport")
    private String typeOfTransport;
    @Column(name = "belonging_to_the_dsi")
    private String belongingToTheDSI;
    @Column(name = "note")
    private String note;
}

package ru.sitronics.tn.document.dto.tableEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(value = {"mtrGroup"})
public class SpecificationTableEntityDto extends BaseTableEntityDto {
    private Long positionNumber;
    private boolean tnlDelivery;
    private Long positionCode;
    private String gostOstTu;
    private String code;
    private BigDecimal priceNoVat;
    private BigDecimal sumNoVat;
    private Double vat;
    private BigDecimal sumVat;
    private BigDecimal amountWithVat;
    private String country;
    private String typeOfTransport;
    private String belongingToTheDSI;
    private String note;
}

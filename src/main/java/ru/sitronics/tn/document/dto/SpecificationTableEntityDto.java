package ru.sitronics.tn.document.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SpecificationTableEntityDto extends BaseTableEntityDto {
    private String pid;
    private Long positionNumber;
    private boolean deliveryMethod;
    private Long positionCode;
    private String mtr;
    private String gostOstTu;
    private String code;
    private String unitOfMeasurement;
    private Long quantity;
    private BigDecimal priceNoVat;
    private BigDecimal sumNoVat;
    private Double vat;
    private BigDecimal sumVat;
    private BigDecimal amountWithVat;
    private String producer;
    private String country;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate deliveryDate;
    private String typeOfTransport;
    private String belongingToTheDSI;
    private String note;
}

package ru.sitronics.tn.document.dto.tableEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(value = {"unitOfMeasurement", "quantity", "deliveryDate"})
public class ProgressOfProductionForShipmentOfMtrTableEntityDto extends BaseTableEntityDto {
    private Long numberPhase;
    private String phase_name;
    private LocalDate planDate;
    private LocalDate factDate;
}

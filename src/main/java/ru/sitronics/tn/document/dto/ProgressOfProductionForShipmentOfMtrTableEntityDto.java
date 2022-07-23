package ru.sitronics.tn.document.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProgressOfProductionForShipmentOfMtrTableEntityDto extends BaseTableEntityDto {
    private String factoryNumber;
    private String mtrGroup;
    private Long numberPhase;
    private String phase_name;
    private LocalDate planDate;
    private LocalDate factDate;
}

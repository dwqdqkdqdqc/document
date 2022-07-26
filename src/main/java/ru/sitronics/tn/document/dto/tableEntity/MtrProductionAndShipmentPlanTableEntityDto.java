package ru.sitronics.tn.document.dto.tableEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MtrProductionAndShipmentPlanTableEntityDto extends BaseTableEntityDto {
    private LocalDate specificationDate;
    private Long specificationNumber;
    private LocalDate productionStartDate;
    private LocalDate shipmentDate;
    private Long productionTimeDays;
    private boolean setDelivery;
    private boolean controlProd;
    private String deliveryMethod;
    @JsonProperty("pidEntities")
    private List<PidEntityDto> pidEntityDtoList;
}

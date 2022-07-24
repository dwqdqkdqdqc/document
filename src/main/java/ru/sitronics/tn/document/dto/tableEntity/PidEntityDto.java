package ru.sitronics.tn.document.dto.tableEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PidEntityDto {
    private UUID id;

    private String factoryNumber;

    @JsonProperty("productionPhases")
    public List<ProductPhaseDto> productPhaseDtoList;
}

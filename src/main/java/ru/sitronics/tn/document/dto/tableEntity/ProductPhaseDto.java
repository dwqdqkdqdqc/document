package ru.sitronics.tn.document.dto.tableEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPhaseDto {
    private UUID id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate planDate;
}

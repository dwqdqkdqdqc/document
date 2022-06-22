package ru.sitronics.tn.document.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class S3FileDto {
    String id;
    String name;
    String error;
}

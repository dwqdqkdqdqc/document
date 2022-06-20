package ru.sitronics.tn.document.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentPageDto extends PageDto {
    private List<DocumentDto> entity;
}

package ru.sitronics.tn.document.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class DocStatusHistoryDto {
    private UUID docId;
    private String status;
    private String version;
    private String createdBy;
    private Instant createdAt;
}

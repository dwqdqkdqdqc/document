package ru.sitronics.tn.document.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import ru.sitronics.tn.document.dto.InfoSupplierChainTableEntityDto;
import ru.sitronics.tn.document.dto.ProgressOfProductionForShipmentOfMtrTableEntityDto;
import ru.sitronics.tn.document.dto.SpecificationTableEntityDto;
import ru.sitronics.tn.document.model.NciDocumentType;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "documentType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SpecificationTableEntityDto.class, name = "SPECIFICATION"),
        @JsonSubTypes.Type(value = InfoSupplierChainTableEntityDto.class,
                name = "INFORMATION_ABOUT_SUPPLIERS_CHAIN_OF_OWNERSHIP"),
        @JsonSubTypes.Type(value = ProgressOfProductionForShipmentOfMtrTableEntityDto.class,
                name = "PROGRESS_OF_PRODUCTION_AND_PREPARATION_FOR_SHIPMENT_OF_MTR")
}) //https://stackoverflow.com/questions/48733012/spring-requestbody-inheritance
public abstract class BaseTableEntityDto {
    @NotNull
    private NciDocumentType.NciDocumentTypeEnum documentType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private String pid;
    private String producer;
    private String mtrName;
}

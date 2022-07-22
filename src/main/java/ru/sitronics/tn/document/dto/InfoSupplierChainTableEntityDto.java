package ru.sitronics.tn.document.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.sitronics.tn.document.dto.base.BaseTableEntityDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InfoSupplierChainTableEntityDto extends BaseTableEntityDto {
    // customer fields
    private String inn;
    private String ogrn;
    private String customerCompanyName;
    private String customerOkvdCode;
    private String customerManagerFio;
    private String customerManagerPassportNumber;
    // contract fields
    private String contractNumberAndDate;
    private String contractSubject;
    private BigDecimal totalIncludingVat;
    private LocalDate startDate;
    private LocalDate endDate;
    private String otherEssentialConditions;
    private String role;
    private String ownerName;
    private String ownerInn;
    private String ownerOgrnOrOgrni;
    private String ownerRegistrationAddress;
    private String documentOfAnIndividual;
    private String supportingDocuments;
    private String legalEntity;
    private String manager;
    private String infoOnTheCompositionOfTheExecBodies;
}

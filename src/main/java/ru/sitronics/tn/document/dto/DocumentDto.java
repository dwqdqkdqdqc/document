package ru.sitronics.tn.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sitronics.tn.document.HasId;
import ru.sitronics.tn.document.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto implements HasId {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // https://stackoverflow.com/a/28025008/548473
    protected String id;
    private String type;
    //private String dType;
    private Long serialNumber;
    private LocalDateTime createDate;
    private LocalDate createDateShort;
    private NciUser author;
    private List<NciUser> curators;
    private byte[] content;
    private NciDocumentStatus status;
    private NciAccessLimitation accessLimitation;
    private String comment;
    private Document contract;
    private Document specification;
    private List<LinkDocumentsTable> relatingDocuments;
    private List<DocumentHistoryBpm> historyBpm;
    private List<NciObjectKisUp> objectKisUps;
    private Integer pidNumber;
    private String factoryNumber;
    private String barcode;
    private String lkkDocumentNumber;
    private LocalDate lkkDocumentDate;
    private String lusDocumentNumber;
    private List<NciAttachment> attachments;
    private String customer;
    private String supplier;
    private BigDecimal amount;
    private List<NciDocumentType> types;
    private Boolean control_prod;
    private NciMtrGroup mtrGroup;


    ////=========================================== other
    private LocalDate additionalAgreementDate;
    private String additionalAgreementNumber;

/*    @OneToOne
    @JsonBackReference(value = "id")
    @JoinColumn(name = "id")
    private MtrSupplyContract additionalAgreementSpecification;*/  //////////
    private NciClassContract classContract;
    private LocalDate startingDate;
    private LocalDate endDate;
    private LocalDate dateOfTermination;
    private NciStandardForm standardForm;
    private Boolean frameworkAgreement;
    private String subjectOfTheContract;
    private NciTerminationCode terminationCode;
    private BigDecimal sumNoVat;
    private BigDecimal sumVat;
    private BigDecimal totalIncludingVat;
    private String statusZakupki;
    private NciOst organization;
    private String role;
    private NciUser responsible;
    private String documentRegistrationNumber;
    private NciOst ost;
    private String contractSubject;
    private String inn;
    private String contractClass;
    private String typicalForm;
    private String contractView;
    private Boolean frameContract;
}

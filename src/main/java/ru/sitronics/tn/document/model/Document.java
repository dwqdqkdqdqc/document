package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sitronics.tn.document.model.base.BaseTableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(javax.persistence.AccessType.FIELD)  //https://stackoverflow.com/a/6084701/548473
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "d_type")
//@DiscriminatorValue("null")
//@JsonIgnoreProperties({ "specification" })
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "documents")
public class Document extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "Specify document type.")
    @Column(name = "type_id")
    private String type;                              //1
    /*  @Column(name = "d_type", insertable = false, updatable = false)
      private String dType;*/

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn
    private Document contract;                           //4
    @OneToOne
    private Document specification;
    @Range(message = "value cannot be lower than 1 or higher than " + Long.MAX_VALUE + " !", min = 1)
    @Column(name = "serial_number", unique = true, nullable = false, insertable = false, updatable = false)
    private Long serialNumber;                       //2
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "create_date", insertable = false, updatable = false)
    private LocalDateTime createDate;               //3
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "create_date", insertable = false, updatable = false)
    private LocalDate createDateShort;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "date_signature")
    private LocalDate dateSignature;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "additional_agreement_date")
    private LocalDate additionalAgreementDate;
    @Column(name = "object_kis_up_id")
    private String objectKisUp;
    @Column(name = "author_id")
    private String author;
    @Column(name = "customer_id")
    private String customer;
    @Column(name = "ost_id")
    private String ost;
    @Column(name = "access_limitation_id")
    private String accessLimitation;
    @Length(message = "a comment cannot be longer than 255 characters!", max = 255)
    @Column(name = "comment")
    private String comment;
    @Column(name = "status")
    private String status;
    @Column(name = "ost_agent_id")
    private String ostAgent;
    @Column(name = "class_contract_id")
    private String classContract;
    @Column(name = "standard_form_id")
    private String standardForm;
    @Column(name = "framework_agreement")
    private Boolean frameworkAgreement;
    @Column(name = "subject_of_the_contract")
    private String subjectOfTheContract;
    @Column(name = "starting_date")
    private LocalDate startingDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "starting_date_work")
    private LocalDate startingDateWork;
    @Column(name = "end_date_work")
    private LocalDate endDateWork;
    @Column(name = "date_of_termination")
    private LocalDate dateOfTermination;
    @Column(name = "termination_code_id")
    private String terminationCode;
    @Column(name = "sum_no_vat")
    private BigDecimal sumNoVat;
    @Column(name = "sum_vat")
    private BigDecimal sumVat;
    @Column(name = "total_sum_vat")
    private BigDecimal totalSumVat;
    @Column(name = "status_zakupki")
    private String statusZakupki;

    // Nci_organization - непонятно, что это (Дог. МТР) //28      ???????????????????

    @Column(name = "role_id")
    private String role;// (тип должен быть Role)


    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private List<DocumentResponsible> responsibles;

    @Column(name = "factory_number")
    private String factoryNumber;
    @Column(name = "pid_number")
    private Integer pidNumber;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "lkk_number")
    private String lkkNumber;
    @Column(name = "lkk_date")
    private LocalDate lkkDate;
    @Column(name = "lus_number")
    private String lusNumber;


    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private List<DocumentRelation> relation;

    @OneToMany(mappedBy = "documentId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<DocumentHistoryBpm> documentsHistoryBpm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dop_Contract_id")
    private Document dopContract;
    @Column(name = "lot_number")
    private String lotNumber;
    @Column(name = "status_contract")
    private String statusContract;
    @Column(name = "nci_consignee_id")
    private String nciConsignee;
    @Column(name = "shipping_details")
    private Integer shippingDetails;
    @Column(name = "position_number")
    private Long positionNumber;
    @Column(name = "delivery_method")
    private boolean deliveryMethod;
    @Column(name = "position_code")
    private Long positionCode;
    @Column(name = "gost_ost_tu")
    private String gostOstTu;
    @Column(name = "code")
    private String code;
    @Column(name = "unit_of_measurement_id")
    private String unitsOfMeasurement;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "price_no_vat")
    private BigDecimal priceNoVat;
    @Column(name = "vat")
    private Double vat;
    @Column(name = "amount_with_vat")
    private BigDecimal amountWithVat;
    @Column(name = "contractor_id")
    private String producer;
    @Column(name = "country_id")
    private String country;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "type_of_transport_id")
    private String typeOfTransport;
    @Column(name = "belonging_to_the_dsi")
    private String belongingToTheDSI;
    @Column(name = "note")
    private String note;
    @Column(name = "customer_inn")
    private String customerInn;
    @Column(name = "customer_ogrn")
    private String customerOgrn;
    @Column(name = "customer_company_name")
    private String customerCompanyName;
    @Column(name = "customer_okved")
    private String customerOkved;
    @Column(name = "customer_manager_fio")
    private String customerManagerFio;
    @Column(name = "customer_passport_number")
    private String customerPassportNumber;
    @Column(name = "contract_number_and_date")
    private String contractNumberAndDate;
    @Column(name = "other_essential_conditions")
    private String otherEssentialConditions;
    @Column(name = "number_in_order")
    private Long numberInOrder;
    @Column(name = "owner_fio")
    private String ownerFio;
    @Column(name = "owner_inn")
    private String ownerInn;
    @Column(name = "owner_ogrn_ogrni")
    private String ownerOgrnOgrni;
    @Column(name = "owner_registration_address")
    private String ownerRegistrationAddress;
    @Column(name = "document_of_individual")
    private String documentOfIndividual;
    @Column(name = "supporting_document")
    private String supportingDocument;
    @Column(name = "legal_entity")
    private String legalEntity;
    @Column(name = "owner_manager_fio")
    private String ownerManagerFio;
    @Column(name = "information_on_composit_executive_bodies")
    private String informationOnCompositExecutiveBodies;
    @Column(name = "policy_period_beginning")
    private LocalDate policyPeriodBeginning;
    @Column(name = "policy_period_end")
    private LocalDate policyPeriodEnd;
    @Column(name = "number_policy")
    private String numberPolicy;
    @Column(name = "date_policy")
    private LocalDate datePolicy;
    @Column(name = "inn_insurance_company")
    private String innInsuranceCompany;
    @Column(name = "name_insurance_company")
    private String nameInsuranceCompany;

/*    @OneToOne
    @JoinColumn(name = "mtr_group_id")
    private NciMtrGroup mtrGroup;*/
    /*      @OneToOne
          @JoinColumn(name = "mtr_id")
          private NciMtr mtr;*/
    @Column(name = "date_supply")
    private LocalDate dateSupply;
    @Column(name = "date_specification")
    private LocalDate dateSpecification;
    @Column(name = "number_specification")
    private Long numberSpecification;
    @Column(name = "date_production")
    private LocalDate dateProduction;
    @Column(name = "date_shipment")
    private LocalDate dateShipment;
    @Column(name = "production_period_days")
    private Integer productionPeriodDays;
    @Column(name = "set_delivery")
    private Boolean setDelivery;
    @Column(name = "phase_id")
    private String phase;
    @Column(name = "plan_date")
    private LocalDate planDate;
    @Column(name = "fact_date")
    private LocalDate factDate;
    @Column(name = "control_prod")
    private Boolean controlProd;
    @Column(name = "confirming_doc")
    private String confirmingDoc;
    @Column(name = "number_phase")
    private Long numberPhase;
    @Column(name = "carrier")
    private String carrier;

/*    @Column(name = "")
    private String ;*/

////////////////////////////////////////////////////////////////// ниже старое
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @JoinTable(name = "documents_curators",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "curator_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "curator_id"}, name = "documents_curators_uc")}
    )
    @OrderBy("lastName")
    private List<NciUser> curators;


    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "content")
    private byte[] content;


    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @JoinTable(name = "documents_attachments",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "attachment_id"}, name = "documents_attachments_uc")})
    private List<NciAttachment> nciAttachments = new ArrayList<>();

    @Column(name = "supplier_id")
    private String supplier;
    @Column(name = "amount")
    private BigDecimal amount;

    @OneToMany
    @JoinColumn(name = "name_rus")
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @OrderBy("nameRus")
    private List<NciDocumentType> nciDocumentTypes;

    // TODO: 11.07.2022 Не удалять
    @Column(name = "deleted")
    private boolean deleted;


    ////=========================================== other

    @Column(name = "additional_agreement_number")
    private String additionalAgreementNumber;

//    @OneToOne
    //  @JsonBackReference(value = "id")
    //   @JoinColumn(name = "id")
    //   private MtrSupplyContract additionalAgreementSpecification;


    @Column(name = "contract_subject")
    private String contractSubject;
    @Column(name = "reg_number")
    private String regNumber;
    @Column(name = "inn")
    private String inn;
    @Column(name = "contract_class")
    private String contractClass;
    @Column(name = "typical_form")
    private String typicalForm;
    @Column(name = "contract_view")
    private String contractView;
    @Column(name = "frame_contract")
    private Boolean frameContract;

    @OneToMany(mappedBy = "document", cascade = {CascadeType.REMOVE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<DocumentAttachment> attachmentFiles;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @JoinColumn(name = "doc_id", referencedColumnName = "id")
    private List<BaseTableEntity> tableEntities;
}

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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(javax.persistence.AccessType.FIELD)  //https://stackoverflow.com/a/6084701/548473
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "d_type")
//@DiscriminatorValue("null")
//@JsonIgnoreProperties({ "specification" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "documents")
public class Document extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "Specify document type.")
    @Column(name = "type_id")
    private String type;

//    @NotNull(message = "Specify document type.")
//    @Column(name = "d_type", insertable = false, updatable = false)
//    private String dType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    private Document contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    private Document specification;





    @Range(message = "value cannot be lower than 1 or higher than " + Long.MAX_VALUE + " !", min = 1)
    @Column(name = "serial_number", unique = true, nullable = false, insertable = false, updatable = false)
    private Long serialNumber;
    @CreatedDate
    //  @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_creation", insertable = false, updatable = false)
    private LocalDateTime dateOfCreation;
    @CreatedDate
    //  @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_creation", insertable = false, updatable = false)
    private LocalDate dateOfCreationShort;
    @CreatedBy
    //  @NotNull
    @ManyToOne
    //   @JsonManagedReference(value = "User")
    @JoinColumn
    private NciUser author;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    //@JsonManagedReference
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
    // @NotNull(message = "Specify the status of the document.")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private NciStatus status;
    @Column(name = "access")
    @Enumerated(EnumType.STRING)
    private NciAccessLimitation access;
    @Length(message = "a comment cannot be longer than 255 characters!", max = 255)
    @Column(name = "comment")
    private String comment;
    /*    @ManyToOne(fetch = FetchType.EAGER) //рабозбрать, почему именно тут не работает eager
        @JoinColumn(*//*nullable = false*//*)
    private MtrSupplyContract contract;*/
/*    @OneToOne
    private Specification specification;*/



/*    @OneToMany(mappedBy = "specification")
    @JsonIgnore
    private List<Document> specDocuments;

    @OneToMany(mappedBy = "contract")
    @JsonIgnore
    private List<Document> ContDocuments;*/


    @OneToMany(mappedBy = "documentId", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    //  @JsonManagedReference
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<RelatingDocumentsTable> relatingDocuments = new ArrayList<>();
    @OneToMany(mappedBy = "documentId")
    @LazyCollection(LazyCollectionOption.FALSE)
    // @JsonManagedReference
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<DocumentHistoryBpm> documentHistoryBpm;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    // @JsonManagedReference
    @BatchSize(size = 100)
    @JoinTable(name = "documents_objects",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "object_kis_up"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "object_kis_up"}, name = "documents_construction_objects_uc")}
    )
    @OrderBy("kisUp")
    private List<NciObject> nciObjects;
    @OneToMany(mappedBy = "documentId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("pid")
    private List<NciPid> pids;
    @OneToMany(mappedBy = "documentId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("factoryNumber")
    private List<NciFactoryNumber> factoryNumber;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "lkk_document_number")
    private String lkkDocumentNumber;
    @Column(name = "lkk_document_date")
    private LocalDate lkkDocumentDate;
    @Column(name = "lus_document_number")
    private String lusDocumentNumber;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    //  @JsonManagedReference
    @BatchSize(size = 100)
    @JoinTable(name = "documents_attachments",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "attachment_id"}, name = "documents_attachments_uc")})
    private List<NciAttachment> nciAttachments = new ArrayList<>();
    @Column(name = "customer_id")
    //  @Enumerated(EnumType.STRING)
    // private Customer customer;
    private String customer;
    @Column(name = "supplier_id")
    //  @Enumerated(EnumType.STRING)
    //  private Supplier supplier;
    private String supplier;
    @Column(name = "amount")
    private BigDecimal amount;
    @OneToMany
    @JoinColumn(name = "name_rus")
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @OrderBy("nameRus")
    private List<NciDocumentType> nciDocumentTypes;
    @Column(name = "deleted")
    private boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nci_phase_id")
    private NciPhase phase;

    ////=========================================== other
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "additional_agreement_date")
    private LocalDate additionalAgreementDate;
    @Column(name = "additional_agreement_number")
    private String additionalAgreementNumber;

//    @OneToOne
    //  @JsonBackReference(value = "id")
    //   @JoinColumn(name = "id")
    //   private MtrSupplyContract additionalAgreementSpecification;

    @OneToOne
    @JoinColumn
    private NciClassContract nciClassContract;
    @Column(name = "starting_date")
    private LocalDate startingDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "date_of_termination")
    private LocalDate dateOfTermination;
    @OneToOne
    @JoinColumn
    private NciStandardForm nciStandardForm;
    @Column(name = "framework_agreement")
    private Boolean frameworkAgreement;
    @Column(name = "subject_of_the_contract")
    private String subjectOfTheContract;
    @OneToOne
    @JoinColumn
    private NciTerminationCode nciTerminationCode;
    @Column(name = "sum_no_vat")
    private BigDecimal sumNoVat;
    @Column(name = "sum_vat")
    private BigDecimal sumVat;
    @Column(name = "total_including_vat")
    private BigDecimal totalIncludingVat;
    @Column(name = "status_zakupki")
    private String statusZakupki;
    @OneToOne
    @JoinColumn
    private NciOst organization;
    @Column(name = "role")
    private String role;
    @OneToOne
    @JoinColumn
    private NciUser responsible;
    // @NotNull
    @Column(name = "document_registration_number")
    private String documentRegistrationNumber;
    //  @NotNull
    @OneToOne
    @JoinColumn
    private NciOst nciOst;
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
}

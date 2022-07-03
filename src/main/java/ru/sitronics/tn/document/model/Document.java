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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "documents")
public class Document extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "Specify document type.")
    @Column(name = "type_id")
    private String type;                                  //1
    /*  @Column(name = "d_type", insertable = false, updatable = false)
      private String dType;*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Document contract;                      //38
    @OneToOne
    private Document specification;                   //4
    @Range(message = "value cannot be lower than 1 or higher than " + Long.MAX_VALUE + " !", min = 1)
    @Column(name = "serial_number", unique = true, nullable = false, insertable = false, updatable = false)
    private Long serialNumber;
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_creation", insertable = false, updatable = false)
    private LocalDateTime dateOfCreation;
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_creation", insertable = false, updatable = false)
    private LocalDate dateOfCreationShort;
    @Column(name = "registration_number")
    private String registrationNumber;                    //2
    @Column(name = "date_signature")
    private LocalDate dateSignature;                       //3
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "additional_agreement_date")
    private LocalDate additionalAgreementDate;             //5
    @OneToOne
    @JoinColumn(name = "object_kis_up_id")
    private NciObjectKisUp objectKisUp;                           //6
    @CreatedBy
    @ManyToOne
    @JoinColumn
    private NciUser author;                           //7
    @Column(name = "customer_id")
    private String customer;                          //8
    @OneToOne
    @JoinColumn
    private NciOst ost;                          //9
    @OneToOne
    @JoinColumn(name = "access_limitation_id")
    private NciAccessLimitation accessLimitation;                          //10
    @Length(message = "a comment cannot be longer than 255 characters!", max = 255)
    @Column(name = "comment")
    private String comment;                         //11
    // @NotNull(message = "Specify the status of the document.")
    @OneToOne
    @JoinColumn(name = "document_status_id")
    private NciDocumentStatus statusDocument;                        //12
    @OneToOne
    @JoinColumn(name = "ost_agent_id")
    private NciOstAgent ostAgent;                        //13
    @OneToOne
    @JoinColumn(name = "class_contract_id")
    private NciClassContract classContract;                        //14
    @OneToOne
    @JoinColumn(name = "standard_form_id")
    private NciStandardForm standardForm;                        //15
    @Column(name = "framework_agreement")
    private Boolean frameworkAgreement;                        //16
    @Column(name = "subject_of_the_contract")
    private String subjectOfTheContract;                      //17
    @Column(name = "starting_date")
    private LocalDate startingDate;                    //18
    @Column(name = "end_date")
    private LocalDate endDate;                  //19
    @Column(name = "starting_date_work")
    private LocalDate startingDateWork;                    //20
    @Column(name = "end_date_work")
    private LocalDate endDateWork;                    //21
    @Column(name = "date_of_termination")
    private LocalDate dateOfTermination;                  //22
    @OneToOne
    @JoinColumn(name = "termination_code_id")
    private NciTerminationCode terminationCode;                  //23
    @Column(name = "sum_no_vat")
    private BigDecimal sumNoVat;                 //24
    @Column(name = "sum_vat")
    private BigDecimal sumVat;                 //25
    @Column(name = "total_including_vat")
    private BigDecimal totalIncludingVat;                 //26
    @Column(name = "status_zakupki")
    private String statusZakupki;               //27

    // Nci_organization - непонятно, что это (Дог. МТР) //28      ???????????????????

    @Column(name = "role_id")
    private String role;                   //29     (тип должен быть Role)
    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private NciUser responsible;                           //30
    @Column(name = "factory_number")
    private String factoryNumber;                         //32
    @Column(name = "pid_number")
    private Integer pidNumber;                        //33
    @Column(name = "barcode")
    private String barcode;                       //34
    @Column(name = "lkk_number")
    private String lkkNumber;                    //35
    @Column(name = "lkk_date")
    private LocalDate lkkDate;                    //36
    @Column(name = "lus_number")
    private String lusNumber;                    //37
    @OneToMany(mappedBy = "documentId", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<LinkDocumentsTable> linkDocuments;  //39
    @OneToMany(mappedBy = "documentId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<DocumentHistoryBpm> documentsHistoryBpm; //40








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
    @Column(name = "deleted")
    private boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nci_phase_id")
    private NciPhase phase;

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
}

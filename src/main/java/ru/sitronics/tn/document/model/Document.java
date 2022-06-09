package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(javax.persistence.AccessType.FIELD)  //https://stackoverflow.com/a/6084701/548473
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "d_type")
@DiscriminatorValue("DOCUMENT")
@Table(name = "documents")
public class Document extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Specify document type.")
    @Column(name = "type_id"/*, insertable = false, updatable = false*/)
    private String type;

    //  @NotNull(message = "Specify document type.")
    @Column(name = "d_type", insertable = false, updatable = false)
    private String dType;

    @Range(message = "value cannot be lower than 1 or higher than " + Long.MAX_VALUE + " !", min = 1)
    @Column(name = "serial_number", unique = true/*, nullable = false, insertable = false, updatable = false*/)
    private Long serialNumber;

    @CreatedDate
    //  @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_creation", insertable = false, updatable = false)
    private LocalDateTime dateOfCreation;

    @CreatedBy
    //  @NotNull
    @ManyToOne(/*optional = false*/)
    //   @JsonManagedReference(value = "User")
    @JoinColumn(/*updatable = false*/)
    private NciUser author;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    //@JsonManagedReference
    @BatchSize(size = 100)
    @JoinTable(name = "documents_curators",
            joinColumns = @JoinColumn(name = "document_id"/*, nullable = false, updatable = false*/),
            inverseJoinColumns = @JoinColumn(name = "curator_id"/*, nullable = false, updatable = false*/),
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

    @ManyToOne(fetch = FetchType.LAZY/*, optional = false*/) //рабозбрать, почему именно тут не работает eager
    @JoinColumn(/*nullable = false*/)
    private Contract contract;

    @ManyToOne
    private Specification  specification;

    @OneToMany(mappedBy = "documentId", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    //  @JsonManagedReference
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<RelatingDocumentsTable> relatingDocuments = new java.util.ArrayList<>();

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
    @JoinTable(name = "documents_construction_objects",
            joinColumns = @JoinColumn(name = "document_id"/*, nullable = false, updatable = false*/),
            inverseJoinColumns = @JoinColumn(name = "construction_object_id"/*, nullable = false, updatable = false*/),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "construction_object_id"}, name = "documents_construction_objects_uc")}
    )
    @OrderBy("name")
    private List<NciConstructionObject> nciConstructionObjects;

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

    @Column(name = "lus_document_number")
    private String lusDocumentNumber;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    //  @JsonManagedReference
    @BatchSize(size = 100)
    @JoinTable(name = "documents_attachments",
            joinColumns = @JoinColumn(name = "document_id"/*, updatable = false*/),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"/*, updatable = false*/),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "attachment_id"}, name = "documents_attachments_uc")})
    private List<NciAttachment> nciAttachments = new java.util.ArrayList<>();

    @Column(name = "customer_id"/*, updatable = false*/)
    //  @Enumerated(EnumType.STRING)
    // private Customer customer;
    private String customer;

    @Column(name = "supplier_id"/*, updatable = false*/)
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



    ////=========================================== Contract
    //  @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_signing"/*, updatable = false*/)
    private LocalDateTime dateOfSigning;

    // @NotNull
    @Column(name = "document_registration_number"/*, updatable = false*/)
    private String documentRegistrationNumber;

    //  @NotNull
    @OneToOne
    @JoinColumn(/*updatable = false*/)
    private NciOst nciOst;

    @Column(name = "contract_subject")
    private String contractSubject;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "inn")
    private String inn;

    @Column(name = "contractor_id")
    private String contractor;

    @Column(name = "contract_class")
    private String contractClass;

    @Column(name = "typical_form")
    private String typicalForm;

    @Column(name = "contract_view")
    private String contractView;

    @Column(name = "frame_contract")
    private Boolean frameContract;

}

package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Access(javax.persistence.AccessType.FIELD)  // https://stackoverflow.com/a/6084701/548473
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "d_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("DOCUMENT")
@Table(name = "documents")
public class Document extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //  @NotNull(message = "Specify document type.")
    @Column(name = "type_id"/*, insertable = false, updatable = false*/)
    // @Enumerated(EnumType.STRING)
    private String type;

    //  @NotNull(message = "Specify document type.")
    @Column(name = "d_type", insertable = false, updatable = false)
  //  @Enumerated(EnumType.STRING)
    private String dType;

    @Range(message = "value cannot be lower than 1 or higher than " + Long.MAX_VALUE + " !", min = 1)
    @Column(name = "serial_number", unique = true/*, nullable = false, insertable = false, updatable = false*/)
    private Long serialNumber;

    @CreatedDate
    //  @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_creation"/*, updatable = false*/)
    private LocalDateTime dateOfCreation;

    @CreatedBy
    //  @NotNull
    @ManyToOne(/*optional = false*/)
    //   @JsonManagedReference(value = "User")
    @JoinColumn(/*updatable = false*/)
    private User author;

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
    private List<User> curators;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "content")
    private byte[] content;

    // @NotNull(message = "Specify the status of the document.")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "access")
    @Enumerated(EnumType.STRING)
    private AccessLimitation access;

    @Length(message = "a comment cannot be longer than 255 characters!", max = 255)
    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY/*, optional = false*/) //рабозбрать, почему именно тут не работает eager
    @JoinColumn(/*nullable = false*/)
    private Contract contract;

    @ManyToOne
    private Specification specification;

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
    private List<ConstructionObject> constructionObjects;

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
    private List<Attachment> attachments = new java.util.ArrayList<>();

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

}

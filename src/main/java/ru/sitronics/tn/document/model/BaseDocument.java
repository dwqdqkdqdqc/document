package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sitronics.tn.document.model.foreignservice.ConstructionObject;
import ru.sitronics.tn.document.model.foreignservice.Contract;
import ru.sitronics.tn.document.model.foreignservice.DocumentHistoryBpm;
import ru.sitronics.tn.document.model.foreignservice.User;
import ru.sitronics.tn.document.model.formalizedDocument.Specification;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(javax.persistence.AccessType.FIELD)  // https://stackoverflow.com/a/6084701/548473
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_doc", discriminatorType = DiscriminatorType.STRING)
@Table(name = "documents")
public class BaseDocument extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Specify document type.")
    @Column(name = "type_doc", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Range(message = "value cannot be lower than 1 or higher than " + Long.MAX_VALUE + " !", min = 1)
    @Column(name = "serial_number", unique = true, nullable = false, insertable = false, updatable = false)
    private long serialNumber;

    @CreatedDate
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "create_date", updatable = false)
    private LocalDateTime creationDate;

    @CreatedBy
    @NotNull
    @ManyToOne(optional = false)
    @JsonManagedReference
    @JoinColumn(name = "create_user", nullable = false, updatable = false)
    private User creator;


    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    @BatchSize(size = 100)
    @JoinTable(name = "documents_curators",
            joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "curator_id", referencedColumnName = "id", nullable = false, updatable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "curator_id"}, name = "documents_curators_uc")}
    )
    @OrderBy("lastName")
    private List<User> curators;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @NotNull(message = "Specify the status of the document.")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "access")
    @Enumerated(EnumType.STRING)
    private AccessLimitation access;

    @Length(message = "a comment cannot be longer than 255 characters!", max = 255)
    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "documentId", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    @BatchSize(size = 100)
    @OrderBy("serialNumber")
    private List<RelatingDocumentsTable> relatingDocuments = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "documentId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("date DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DocumentHistoryBpm> documentHistoryBpm;

    @NotNull(message = "Specify a link to the current document's related contract.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "contract")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "specification")
    private Specification specification;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    @BatchSize(size = 100)
    @JoinTable(name = "documents_construction_objects",
            joinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "construction_object_id", referencedColumnName = "id", nullable = false, updatable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "construction_object_id"}, name = "documents_construction_objects_uc")}
    )
    @OrderBy("name")
    @JoinColumn(name = "construction_object")
    private List<ConstructionObject> constructionObjects;

    @CollectionTable(name = "pids", joinColumns = @JoinColumn(name = "document_id"))
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("pid")
    // @JoinColumn(name = "document_id")  //https://stackoverflow.com/a/62848296/548473
    @Column(name = "pid")
    private List<String> pids;

    @CollectionTable(name = "factory_numbers", joinColumns = @JoinColumn(name = "document_id"))
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("factory_number")
    @Column(name = "factory_number")
    private List<String> factoryNumber;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "lkk_document_number")
    private String lkkDocumentNumber;

    @Column(name = "lus_document_number")
    private String lusDocumentNumber;
}

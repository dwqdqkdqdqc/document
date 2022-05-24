package ru.sitronics.tn.document.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(javax.persistence.AccessType.FIELD)  //https://stackoverflow.com/a/6084701/548473

@Table(name = "documents")
public class Doc extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Specify document type.")
    @Column(name = "type_id"/*, insertable = false, updatable = false*/)
    private String type;

    //  @NotNull(message = "Specify document type.")
/*    @Column(name = "dtype", insertable = false, updatable = false)
    private String dType;*/

    @Range(message = "value cannot be lower than 1 or higher than " + Long.MAX_VALUE + " !", min = 1)
    @Column(name = "serial_number", unique = true)
    private Long serialNumber;

    @CreatedBy
    //  @NotNull
    @ManyToOne()
    //   @JsonManagedReference(value = "User")
    @JoinColumn()
    private User author;

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

    @ManyToOne(fetch = FetchType.LAZY) //рабозбрать, почему именно тут не работает eager
    @JoinColumn()
    private Document contract;

    @ManyToOne(fetch = FetchType.LAZY)
    private Document  specification;

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
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "construction_object_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"document_id", "construction_object_id"}, name = "documents_construction_objects_uc")}
    )
    @OrderBy("name")
    private List<ConstructionObject> constructionObjects;
}

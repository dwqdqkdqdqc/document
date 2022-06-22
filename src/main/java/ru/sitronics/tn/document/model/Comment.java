package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class) // https://stackoverflow.com/questions/40370709/createddate-annotation-does-not-work-with-mysql
public class Comment extends BaseEntity {

    @Column(name = "comment")
    private String comment;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_creation", updatable = false)
    private LocalDateTime dateOfCreation;

    @LastModifiedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_modification")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "comment", cascade = {CascadeType.REMOVE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CommentAttachment> attachments;

    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonIgnore
    private Document document;
}

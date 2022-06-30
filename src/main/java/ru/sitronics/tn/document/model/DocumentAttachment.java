package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "doc_attachments")
public class DocumentAttachment extends BaseEntity {

    public DocumentAttachment(String fileName, String fileId, String author, Document document) {
        this.fileName = fileName;
        this.fileId = fileId;
        this.author = author;
        this.document = document;
    }

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_of_uploading", updatable = false)
    private LocalDateTime dateOfUploading;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonIgnore
    private Document document;
}

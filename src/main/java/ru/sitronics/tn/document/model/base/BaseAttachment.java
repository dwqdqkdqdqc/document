package ru.sitronics.tn.document.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.sitronics.tn.document.model.BaseEntity;
import ru.sitronics.tn.document.model.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseAttachment extends BaseEntity {

    public BaseAttachment(String fileName, String fileId, String author) {
        this.fileName = fileName;
        this.fileId = fileId;
        this.author = author;
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
}

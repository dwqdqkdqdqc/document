package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.sitronics.tn.document.model.base.BaseAttachment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "doc_attachments")
public class DocumentAttachment extends BaseAttachment {
    public DocumentAttachment(String fileName, String fileId, String author, Document document) {
        super(fileName, fileId, author);
        this.document = document;
    }

    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonIgnore
    private Document document;
}

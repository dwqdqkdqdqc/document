package ru.sitronics.tn.document.model.foreignservice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.sitronics.tn.document.model.BaseEntity;
import ru.sitronics.tn.document.model.BaseDocument;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(callSuper = true)
@Table(name = "doc_history_bpm")
public class DocumentHistoryBpm extends BaseEntity {
    @Column(name = "author")
    private String author;
    @Column(name = "event")
    private String event;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "statuses")
    private String statuses;
    @Column(name = "bpmn_statuses")
    private String bpmnStatus;
    @Column(name = "comments")
    private String comments;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    @JsonBackReference
    private List<BaseDocument> createdDocuments;
    @NotNull(message = "Document_id must be specified.")
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "id_doc", unique = true, updatable = false)
    BaseDocument documentId;
}

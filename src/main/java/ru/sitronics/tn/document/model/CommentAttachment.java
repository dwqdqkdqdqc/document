package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment_attachments")
public class CommentAttachment extends BaseEntity {

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_id")
    private String fileId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Comment comment;


}

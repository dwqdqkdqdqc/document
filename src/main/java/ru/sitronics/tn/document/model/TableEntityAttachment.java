package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sitronics.tn.document.model.base.BaseAttachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_entity_attachments")
public class TableEntityAttachment extends BaseAttachment {
    public TableEntityAttachment(String fileName, String fileId, String author, UUID tableEntityId) {
        super(fileName, fileId, author);
        this.tableEntityId = tableEntityId;
    }

    @Column(name = "table_entity_id")
    @JsonIgnore
    private UUID tableEntityId;
}

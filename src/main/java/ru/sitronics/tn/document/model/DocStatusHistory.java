package ru.sitronics.tn.document.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import ru.sitronics.tn.document.model.base.BaseEntityUUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "doc_status_history")
public class DocStatusHistory extends BaseEntityUUID {
    @NotNull
    private String docId;
    @NotNull
    private String status;
//    @NotNull
//    private String version;
    @NotNull
    private String createdBy;
//    @CreationTimestamp
//    private Instant createdAt = Instant.now();
//    private String modifiedBy;
//    private Instant modifiedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DocStatusHistory that = (DocStatusHistory) o;
        return super.equals(o)
                && Objects.equals(docId, that.getDocId())
                && Objects.equals(status, that.getStatus())
//                && Objects.equals(version, that.getVersion())
                && Objects.equals(createdBy, that.getCreatedBy())
                && Objects.equals(createdAt, that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

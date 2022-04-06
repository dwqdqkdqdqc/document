package ru.sitronics.tn.document.model.foreignservice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.sitronics.tn.document.model.BaseEntity;
import ru.sitronics.tn.document.model.BaseDocument;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(callSuper = true)
@Table(name = "contracts")
public class Contract extends BaseEntity {
    @Column(name = "responsible_user_id")
    private String responsibleUserId;
    @Column(name = "organization_id")
    private String organizationId;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "contract", orphanRemoval = true)
    @JsonBackReference
    private List<BaseDocument> createdDocuments = new java.util.ArrayList<>();
}

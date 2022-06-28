package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_types_of_transport")
public class NciTypeOfTransport extends BaseEntity {

    @Column(name = "type_of_transport")
    private String typeOfTransport;

    @Column(name = "internal_id")
    private int internalId;

/*    @OneToMany(mappedBy = "nciTypeOfTransport")
    @JsonIgnore
    private List<SpecificationTableEntity> specificationTableEntityList;*/
}

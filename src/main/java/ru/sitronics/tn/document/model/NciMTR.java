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
@Table(name = "nci_mtrs")
public class NciMTR extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "internal_id")
    private int internalId;

    @Column(name = "control_prod")
    private boolean controlProd;

    @OneToMany(mappedBy = "nciMTR")
    @JsonIgnore
    private List<SpecificationTableEntity> specificationTableEntityList;
}

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
@Table(name = "nci_units_of_measurement")
public class NciUnitsOfMeasurement extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "internal_id")
    private int internalId;

    @OneToMany(mappedBy = "nciUnitsOfMeasurement")
    @JsonIgnore
    private List<SpecificationTableEntity> specificationTableEntityList;
}

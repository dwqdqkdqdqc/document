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
@Table(name = "nci_countries")
public class NciCountry extends BaseEntity {

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "internal_id")
    private int internalId;

/*    @OneToMany(mappedBy = "nciCountry")
    @JsonIgnore
    private List<SpecificationTableEntity> specificationTableEntityList;*/
}

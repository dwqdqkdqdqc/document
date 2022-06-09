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

    @Column(name = "display_value")
    private String displayValue;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "internal_id")
    private int internalId;

    @Column(name = "value")
    private String value;

    @OneToMany(mappedBy = "nciMTR")
    @JsonIgnore
    private List<SpecificationsTablesEntity> specificationsTablesEntityList;
}

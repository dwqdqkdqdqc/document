package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_standard_forms")
public class NciStandardForm extends BaseEntity {
    @Column(name = "nci_standard_form")
    private String nciStandardForm;
    @Column(name = "internal_id")
    private Integer internalId;
}

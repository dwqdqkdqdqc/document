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
@Table(name = "nci_mtr_group")
public class NciMtrGroup extends BaseEntity {
    @Column(name = "name_group")
    private String nameGroup;
    @Column(name = "internal_id")
    private int internalId;
}

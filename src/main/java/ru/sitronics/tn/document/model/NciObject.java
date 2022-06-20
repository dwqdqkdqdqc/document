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
@Table(name = "nci_objects")
public class NciObject extends BaseEntity {
    @Column(name = "kis_up")
    private String kisUp;
    @Column(name = "kis_up_id")
    private String kisUpId;
}

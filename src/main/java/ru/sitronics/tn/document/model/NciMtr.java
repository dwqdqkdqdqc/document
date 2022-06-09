package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_mtr")
public class NciMtr extends BaseEntity {
    @Column(name = "name_mtr")
    private String nameMtr;
    @ManyToOne
    @JoinColumn(name = "nci_mtr_group_id")
    private NciMtrGroup nciMtrGroup;
}

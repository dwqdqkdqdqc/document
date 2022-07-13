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
@Table(name = "nci_mtr_groups")
public class NciMtrGroup extends BaseEntity {
    @Column(name = "mtr_group")
    private String mtrGroup;
    @Column(name = "internal_id")
    private Integer internalId;
    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "documents",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Document documentMtrGroup;
}

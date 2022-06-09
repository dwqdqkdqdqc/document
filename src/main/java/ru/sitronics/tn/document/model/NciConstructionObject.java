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
@Table(name = "construction_objects")
public class NciConstructionObject extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "organization")
    private String organization;
}

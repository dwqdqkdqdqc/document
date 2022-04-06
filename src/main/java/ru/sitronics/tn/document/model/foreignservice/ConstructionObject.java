package ru.sitronics.tn.document.model.foreignservice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sitronics.tn.document.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "construction_objects")
public class ConstructionObject extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "organization")
    private String organization;
}

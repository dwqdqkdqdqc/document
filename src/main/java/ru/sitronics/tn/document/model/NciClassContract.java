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
@Table(name = "nci_class_contracts")
public class NciClassContract extends BaseEntity{
    @Column(name = "class_contract")
    private String classContract;
    @Column(name = "code")
    private String code;
}

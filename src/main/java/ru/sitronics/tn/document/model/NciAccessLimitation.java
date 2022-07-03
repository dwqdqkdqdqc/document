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
@Table(name = "nci_access_limitations")
public class NciAccessLimitation extends BaseEntity{
    @Column(name = "access_limitation")
    private String accessLimitation;
    @Column(name = "code")
    private String code;
}

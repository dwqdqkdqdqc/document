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
@Table(name = "nci_termination_codes")
public class NciTerminationCode extends BaseEntity{
    @Column(name = "termination_code")
    private String terminationCode;
    @Column(name = "internal_id")
    private Integer internalId;
}

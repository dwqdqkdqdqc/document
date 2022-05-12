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
@Table(name = "nci_factory_numbers")
public class NciFactoryNumber extends BaseEntity {
    @Column(name = "factory_number")
    private String factoryNumber;
    @Column(name = "document_id")
    private String documentId;
}

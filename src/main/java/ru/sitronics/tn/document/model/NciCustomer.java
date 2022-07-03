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
@Table(name = "nci_customers")
public class NciCustomer extends BaseEntity {
    @Column(name = "customer")
    private String customer;
    @Column(name = "customer_rus")
    private String customerRus;
    @Column(name = "internal_id")
    private Integer internalId;
    @Column(name = "internal_guid")
    private Integer internalGuid;
    @Column(name = "inn")
    private String inn;
    @Column(name = "kpp")
    private String kpp;
    @Column(name = "okpo")
    private String okpo;
    @Column(name = "okdp")
    private String okdp;
    @Column(name = "ogrn")
    private String ogrn;
    @Column(name = "okved")
    private String okved;
    @Column(name = "okato")
    private String okato;
    @Column(name = "oktmo")
    private String oktmo;
    @Column(name = "phone")
    private String phone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "customer_type")
    private Integer customerType;
    @Column(name = "bp_type_lt_id")
    private Integer bp_typeLtId;
}

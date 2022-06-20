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
@Table(name = "nci_delivery_methods")
public class NciDeliveryMethod extends BaseEntity {
    @Column(name = "delivery_method")
    private String deliveryMethod;
    @Column(name = "internal_id")
    private Integer internalId;
}

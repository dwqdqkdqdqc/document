package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MTR_INSURANCE_POLICY")
public class MtrInsurancePolicy extends Document{

    @Column(name = "sum_no_vat")
    private BigDecimal sumNoVat;
    @Column(name = "sum_vat")
    private BigDecimal sumVat;
    @Column(name = "total_including_vat")
    private BigDecimal totalIncludingVat;
    @Column(name = "commencement_date")
    private LocalDate commencementDate;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "number_policy")
    private String numberPolicy;
    @Column(name = "data_policy")
    private LocalDate dataPolicy;
}

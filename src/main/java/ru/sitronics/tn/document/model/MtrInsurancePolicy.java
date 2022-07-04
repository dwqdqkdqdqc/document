package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
/*

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MTR_INSURANCE_POLICY")
public class MtrInsurancePolicy extends Document {

    @Column(name = "starting_date")
    private LocalDate startingDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "number_policy")
    private String numberPolicy;
    @Column(name = "data_policy")
    private LocalDate dataPolicy;
}*/

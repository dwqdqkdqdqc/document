package ru.sitronics.tn.document.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ACTS_OF_DETECTED_DEFECTS_OR_PRODUCT_FAILURES_WITHIN_THE_SCOPE_OF_THE_WARRANTY_CASE")

public class ActsOnDetectedDefects1 extends Document {



}

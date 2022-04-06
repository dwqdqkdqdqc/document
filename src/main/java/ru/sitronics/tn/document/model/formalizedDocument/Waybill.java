package ru.sitronics.tn.document.model.formalizedDocument;

import ru.sitronics.tn.document.model.BaseDocument;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WAYBILL")
public class Waybill extends BaseDocument {
}

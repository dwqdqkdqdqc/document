package ru.sitronics.tn.document.model.formalizedDocument;

import ru.sitronics.tn.document.model.BaseDocument;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SPECIFICATION")
public class Specification extends BaseDocument {
    // private AdditionalAgreement additionalAgreement; //В прил.B есть только: 36)Соглашение об определении работ (поставок материалов), в счет которых уплачен аванс (часть аванса) (4.24)
/*    @Column(name = "lot")
    private String lot;
    @OneToMany(mappedBy = "specification")
    @JsonIgnore
    private List<BaseDocument> documents;*/
}

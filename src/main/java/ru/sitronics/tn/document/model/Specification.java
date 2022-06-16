package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("SPECIFICATION")
public class Specification extends Document {
    // private AdditionalAgreement additionalAgreement; //В прил.B есть только: 36)Соглашение об определении работ (поставок материалов), в счет которых уплачен аванс (часть аванса) (4.24)
    @Column(name = "lot")
    private String lot;

/*    @OneToMany(mappedBy = "specification")
    private List<Document> documents;*/
}

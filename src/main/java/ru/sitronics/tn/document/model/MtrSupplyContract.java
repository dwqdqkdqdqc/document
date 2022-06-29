package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
/*

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MTR_SUPPLY_CONTRACT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MtrSupplyContract extends Document {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_signing"
            , updatable = false
    )
    private LocalDate dateOfSigning;


    @Column(name = "document_registration_number"
            , updatable = false
    )
    private String documentRegistrationNumber;


    @OneToOne
    @JoinColumn(
            updatable = false
    )
    private NciOst nciOst;

    @OneToMany(mappedBy = "contract")
    private List<Document> documents;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "specification_id")
    private Specification specification;
}
*/

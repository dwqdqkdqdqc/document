package ru.sitronics.tn.document.model;

/*import com.fasterxml.jackson.annotation.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@DiscriminatorValue("SPECIFICATION")
@JsonIgnoreProperties(value = {"specification", "dateOfSigning", "documentRegistrationNumber", "contractSubject",
        "regNumber", "inn", "contractClass", "typicalForm", "contractView", "frameContract"})
public class Specification extends Document {

    @Column(name = "lot")
    private String lot;

    @Column(name = "contract_status")
    private String contractStatus;

    @OneToMany(mappedBy = "specification")
    @JsonBackReference
    private List<Document> documents;


//    @OneToMany(mappedBy = "specification")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OrderBy("positionNumber")
//    @JsonManagedReference
//    private List<SpecificationTableEntity> specificationsTablesEntities;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dop_contract_id")
//    private MtrSupplyContract dopContract;

    @ManyToOne
    @JoinColumn(name = "nci_consignee_id")
    private NciConsignee nciConsignee;

    @Column(name = "shipping_details")
    private String shippingDetails;
}*/

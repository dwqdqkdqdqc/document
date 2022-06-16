package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@DiscriminatorValue("SPECIFICATION")
@JsonIgnoreProperties(value = {"specification", "dateOfSigning", "documentRegistrationNumber", "contractSubject",
        "regNumber", "inn", "contractClass", "typicalForm", "contractView", "frameContract"})
public class Specification extends Document {

    @Column(name = "lot")
    private String lot;

    @Column(name = "sum_no_vat")
    private BigDecimal totalSumNoVat;

    @Column(name = "sum_vat")
    private BigDecimal totalVat;

    @Column(name = "total_including_vat")
    private BigDecimal totalSumVat;

    @Column(name = "contract_status")
    private String contractStatus;

    @OneToMany(mappedBy = "specification")
    @JsonIgnore
    private List<Document> documents;

    @OneToMany(mappedBy = "specification")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("positionNumber")
    @JsonManagedReference
    private List<SpecificationTableEntity> specificationsTablesEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dop_contract_id")
    private Contract dopContract;

    @ManyToOne
    @JoinColumn(name = "nci_consignee_id")
    private NciConsignee nciConsignee;

    @Column(name = "shipping_details")
    private String shippingDetails;
}

package ru.sitronics.tn.document.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sitronics.tn.document.model.base.BaseTableEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("INFORMATION_ABOUT_SUPPLIERS_CHAIN_OF_OWNERSHIP")
public class InfoSupplierChainTableEntity extends BaseTableEntity {
    // customer fields
    @Column(name = "inn")
    private String inn;
    @Column(name = "ogrn")
    private String ogrn;
    @Column(name = "customer_company_name")
    private String customerCompanyName;
    @Column(name = "customer_okvd_code")
    private String customerOkvdCode;
    @Column(name = "customer_manager_fio")
    private String customerManagerFio;
    @Column(name = "customer_manager_passport_number")
    private String customerManagerPassportNumber;
    // contract fields
    @Column(name = "contract_number_and_date")
    private String contractNumberAndDate;
    @Column(name = "contract_subject")
    private String contractSubject;
    @Column(name = "total_including_vat")
    private BigDecimal totalIncludingVat;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "other_essential_conditions")
    private String otherEssentialConditions;
    // info supp chain fields
    @Column(name = "number_in_order")
    private Long numberInOrder;
    @Column(name = "role")
    private String role;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "owner_inn")
    private String ownerInn;
    @Column(name = "owner_ogrn_or_ogrni")
    private String ownerOgrnOrOgrni;
    @Column(name = "owner_registration_address")
    private String ownerRegistrationAddress;
    @Column(name = "document_of_an_individual")
    private String documentOfAnIndividual;
    @Column(name = "supporting_documents")
    private String supportingDocuments;
    @Column(name = "legal_entity")
    private String legalEntity;
    @Column(name = "manager")
    private String manager;
    @Column(name = "info_compos_exec_bodies")
    private String infoOnTheCompositionOfTheExecBodies;
}

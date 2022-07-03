package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_consignees")
public class NciConsignee extends BaseEntity {
    @Column(name = "bukrs")
    private String bukrs;
    @Column(name = "external_consignee_id")
    private String externalConsigneeId;
    @Column(name = "first_consignee_name")
    private String firstConsigneeName;
    @Column(name = "second_consignee_name")
    private String secondConsigneeName;
    @Column(name = "third_consignee_name")
    private String thirdConsigneeName;
    @Column(name = "fourth_consignee_name")
    private String fourthConsigneeName;
    @Column(name = "inn")
    private String inn;
    @Column(name = "kpp")
    private String kpp;
    @Column(name = "okpo")
    private String okpo;
    @Column(name = "first_railway_code")
    private String firstRailwayCode;
    @Column(name = "first_freight_yard")
    private String firstFreightYard;
    @Column(name = "first_station_code")
    private String firstStationCode;
    @Column(name = "first_customer_code")
    private String firstCustomerCode;
    @Column(name = "second_railway_code")
    private String secondRailwayCode;
    @Column(name = "second_station_code")
    private String secondStationCode;
    @Column(name = "second_freight_yard")
    private String secondFreightYard;
    @Column(name = "second_customer_code")
    private String secondCustomerCode;
    @Column(name = "third_railway_code")
    private String thirdRailwayCode;
    @Column(name = "third_station_code")
    private String thirdStationCode;
    @Column(name = "third_customer_code")
    private String thirdCustomerCode;
    @Column(name = "fourth_railway_code")
    private String fourthRailwayCode;
    @Column(name = "fourth_station_code")
    private String fourthStationCode;
    @Column(name = "fourth_customer_code")
    private String fourthCustomerCode;
    @Column(name = "fifth_railway_code")
    private String fifthRailwayCode;
    @Column(name = "fifth_station_code")
    private String fifthStationCode;
    @Column(name = "fifth_customer_code")
    private String fifthCustomerCode;
    @Column(name = "first_post_code")
    private String firstPostCode;
    @Column(name = "first_country")
    private String firstCountry;
    @Column(name = "first_region")
    private String firstRegion;
    @Column(name = "first_city")
    private String firstCity;
    @Column(name = "first_street")
    private String firstStreet;
    @Column(name = "first_street_suppl")
    private String firstStreetSuppl;
    @Column(name = "first_house_number")
    private String firstHouseNumber;
    @Column(name = "first_house_number_suppl")
    private String firstHouseNumberSuppl;
    @Column(name = "second_post_code")
    private String secondPostCode;
    @Column(name = "second_country")
    private String secondCountry;
    @Column(name = "second_region")
    private String secondRegion;
    @Column(name = "second_city")
    private String secondCity;
    @Column(name = "second_street")
    private String secondStreet;
    @Column(name = "second_street_suppl")
    private String secondStreetSuppl;
    @Column(name = "second_house_number")
    private String secondHouseNumber;
    @Column(name = "second_house_number_suppl")
    private String secondHouseNumberSuppl;
    @Column(name = "sixth_railway_code")
    private String sixthRailwayCode;
    @Column(name = "sixth_station_code")
    private String sixthStationCode;
    @Column(name = "sixth_customer_code")
    private String sixthCustomerCode;
    @Column(name = "department")
    private String department;
    @Column(name = "function")
    private String function;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "tel_number")
    private String telNumber;
    @Column(name = "tel_extension")
    private String telExtension;
    @Column(name = "fax_number")
    private String faxNumber;
    @Column(name = "fax_extension")
    private String faxExtension;
    @Column(name = "fourth_country")
    private String fourthCountry;
    @Column(name = "fourth_region")
    private String fourthRegion;
    @Column(name = "fourth_district")
    private String fourthDistrict;
    @Column(name = "fourth_city")
    private String fourthCity;

/*    @OneToMany(mappedBy = "nciConsignee")
    @JsonIgnore
    private List<Specification> specifications;*/
}

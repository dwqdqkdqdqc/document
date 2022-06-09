package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_consignees")
public class NciConsignee extends BaseEntity {

    @Column(name = "bukrs")
    private String bukrs;

    @Column(name = "sortl")
    private String sortl;

    @Column(name = "name_1")
    private String name1;

    @Column(name = "name_2")
    private String name2;

    @Column(name = "name_3")
    private String name3;

    @Column(name = "name_4")
    private String name4;

    @Column(name = "zzsrm_stcd_1")
    private String zzsrmStcd1;

    @Column(name = "zzsrm_stcd_2")
    private String zzsrmStcd2;

    @Column(name = "zzsrm_stcd_3")
    private String zzsrmStcd3;

    @Column(name = "zzsrm_rw_code_1")
    private String zzsrmRwCode1;

    @Column(name = "zzsrm_rw_freight_yard_1")
    private String zzsrmRwFreightYard1;

    @Column(name = "zzsrm_rw_station_code_1")
    private String zzsrmRwStationCode1;

    @Column(name = "zzsrm_rw_cuscode_1")
    private String zzsrmRwCuscode1;

    @Column(name = "zzsrm_rw_code_2")
    private String zzsrmRwCode2;

    @Column(name = "zzsrm_rw_station_code_2")
    private String zzsrmRwStationCode2;

    @Column(name = "zzsrm_rw_freight_yard_2")
    private String zzsrmRwFreightYard2;

    @Column(name = "zzsrm_rw_cuscode_2")
    private String zzsrmRwCuscode2;

    @Column(name = "zzsrm_rw_code_3")
    private String zzsrmRwCode3;

    @Column(name = "zzsrm_rw_station_code_3")
    private String zzsrmRwStationCode3;

    @Column(name = "zzsrm_rw_cuscode_3")
    private String zzsrmRwCuscode3;

    @Column(name = "zzsrm_rw_code_4")
    private String zzsrmRwCode4;

    @Column(name = "zzsrm_rw_station_code_4")
    private String zzsrmRwStationCode4;

    @Column(name = "zzsrm_rw_cuscode_4")
    private String zzsrmRwCuscode4;

    @Column(name = "zzsrm_rw_code_5")
    private String zzsrmRwCode5;

    @Column(name = "zzsrm_rw_station_code_5")
    private String zzsrmRwStationCode5;

    @Column(name = "zzsrm_rw_cuscode_5")
    private String zzsrmRwCuscode5;

    @Column(name = "zzsrm_post_code")
    private String zzsrmPostCode;

    @Column(name = "zzsrm_country")
    private String zzsrmCountry;

    @Column(name = "zzsrm_region")
    private String zzsrmRegion;

    @Column(name = "zzsrm_city")
    private String zzsrmCity;

    @Column(name = "zzsrm_street")
    private String zzsrmStreet;

    @Column(name = "zzsrm_str_suppl")
    private String zzsrmStrSuppl;

    @Column(name = "zzsrm_house_num")
    private String zzsrmHouseNum;

    @Column(name = "zzsrm_house_num2")
    private String zzsrmHouseNum2;

    @Column(name = "zzsrm_post_code2")
    private String zzsrmPostCode2;

    @Column(name = "zzsrm_country2")
    private String zzsrmCountry2;

    @Column(name = "zzsrm_region2")
    private String zzsrmRegion2;

    @Column(name = "zzsrm_city2")
    private String zzsrmCity2;

    @Column(name = "zzsrm_street2")
    private String zzsrmStreet2;

    @Column(name = "zzsrm_str_suppl2")
    private String zzsrmStrSuppl2;

    @Column(name = "zzsrm_house_num_2")
    private String zzsrmHouseNum_2;

    @Column(name = "zzsrm_house_num2_2")
    private String zzsrmHouseNum2_2;

    @Column(name = "zzsrm_rw_code_6")
    private String zzsrmRwCode6;

    @Column(name = "zzsrm_rw_station_code_6")
    private String zzsrmRwStationCode6;

    @Column(name = "zzsrm_rw_cuscode_6")
    private String zzsrmRwCuscode6;

    @Column(name = "department")
    private String department;

    @Column(name = "function")
    private String function;

    @Column(name = "name_last")
    private String nameLast;

    @Column(name = "name_first")
    private String nameFirst;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "tel_extens")
    private String telExtens;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "fax_extens")
    private String faxExtens;

    @Column(name = "zzsrm_country_4")
    private String zzsrmCountry4;

    @Column(name = "zzsrm_region_4")
    private String zzsrmRegion4;

    @Column(name = "zzsrm_district_4")
    private String zzsrmDistrict4;

    @Column(name = "zzsrm_city_4")
    private String zzsrmCity4;


    @OneToMany(mappedBy = "nciConsignee")
    @JsonIgnore
    private List<Specification> specifications;
}

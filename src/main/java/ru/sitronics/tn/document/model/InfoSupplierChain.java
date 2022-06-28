package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
/*

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@DiscriminatorValue("INFORMATION_ABOUT_SUPPLIERS_CHAIN_OF_OWNERSHIP")
public class InfoSupplierChain extends Document{

    @ManyToOne
    private MtrSupplyContract contract;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_signing", updatable = false)
    private LocalDateTime dateOfSigning;

    @ManyToOne
    private Specification specification;

    //Таблчная часть [Тянется из справочника АСУ закупки]
    
    //Служебные атрибуты???

}*/

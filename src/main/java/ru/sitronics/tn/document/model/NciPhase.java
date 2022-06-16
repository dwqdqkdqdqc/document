package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_phases")
public class NciPhase extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "name_rus")
    private String nameRus;


//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name="quality_document_id")
//    private QualityDocument qualityDocument;
}

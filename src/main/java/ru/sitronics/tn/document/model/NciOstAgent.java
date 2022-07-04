package ru.sitronics.tn.document.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "nci_ost_agents")
public class NciOstAgent extends BaseEntity{
    @Column(name = "ost_agent")
    private String ostAgent;
    @Column(name = "internal_id")
    private Integer internalId;
}

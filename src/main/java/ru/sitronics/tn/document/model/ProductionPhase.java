package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sitronics.tn.document.model.base.BaseEntityUUID;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "production_phase")
public class ProductionPhase extends BaseEntityUUID {

    public ProductionPhase(int orderNum, String phaseName, PidEntity pidEntity) {
        this.orderNum = orderNum;
        this.phaseName = phaseName;
        this.pidEntity = pidEntity;
    }

    @Column(name = "order_num")
    private int orderNum;
    @Column(name = "phase_name")
    private String phaseName;
    @Column(name = "plan_date")
    private LocalDate planDate;


    @ManyToOne
    @JoinColumn(name = "pid_entity_id")
    @JsonIgnore
    @JsonBackReference
    private PidEntity pidEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionPhase)) return false;
        if (!super.equals(o)) return false;
        ProductionPhase that = (ProductionPhase) o;
        return orderNum == that.orderNum && Objects.equals(phaseName, that.phaseName) && Objects.equals(planDate, that.planDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.id, orderNum, phaseName, planDate);
    }
}

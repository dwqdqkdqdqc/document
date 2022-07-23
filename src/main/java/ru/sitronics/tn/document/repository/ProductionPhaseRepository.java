package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.ProductionPhase;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductionPhaseRepository extends JpaRepository<ProductionPhase, UUID> {
    @Query(value = "select full_value from dictionaries.nci_production_stage where mtr_group = ?1 order by ord",
            nativeQuery = true)
    List<String> getPhaseNamesByMtrGroup(String mtrGroup);
}

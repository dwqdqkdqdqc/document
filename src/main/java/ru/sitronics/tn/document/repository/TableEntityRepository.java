package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.base.BaseTableEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableEntityRepository extends JpaRepository<BaseTableEntity, UUID> {
    @Query(value = "SELECT MAX(number_in_order) FROM table_entities WHERE doc_id = ?1", nativeQuery = true)
    Optional<Long> findMaxNumberInOrderByDocId(String docId);

    @Query(value = "SELECT t.tableEntityType FROM BaseTableEntity t WHERE t.id = ?1")
    String getTableEntityTypeById(UUID tableEntityId);
}

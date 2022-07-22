package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.base.BaseTableEntity;

import java.util.UUID;

@Repository
public interface TableEntityRepository extends JpaRepository<BaseTableEntity, UUID> {
}

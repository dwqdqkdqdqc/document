package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.PidEntity;

import java.util.UUID;

@Repository
public interface PidEntityRepository extends JpaRepository<PidEntity, UUID> {
}

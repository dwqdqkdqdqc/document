package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.BaseDocument;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<BaseDocument, String> {

    BaseDocument findBySerialNumber(long number);

    List<BaseDocument> findBySerialNumberContaining(long number);

    List<BaseDocument> findBySerialNumberOrderByCreator(long number);

    List<BaseDocument> findBySerialNumberOrderByCurators(long number);

    List<BaseDocument> findByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<BaseDocument> findByCreationDateGreaterThanEqual(LocalDateTime startDate);

    List<BaseDocument> findByCreationDateLessThanEqual(LocalDateTime startDate);
}

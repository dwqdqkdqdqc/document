package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.Document;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String>, JpaSpecificationExecutor<Document> {
    @Modifying
    @Query("UPDATE Document d SET d.deleted = true WHERE d.id = ?1")
    void markDocumentAsDeletedById(String id);

    Optional<Document> findByIdAndDeleted(String id, boolean deleted);
}
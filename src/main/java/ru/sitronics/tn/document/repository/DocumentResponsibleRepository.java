package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.DocumentResponsible;

@Repository
public interface DocumentResponsibleRepository extends JpaRepository<DocumentResponsible, String> {

    Boolean existsDocumentResponsibleByDocumentId (String documentId);
}

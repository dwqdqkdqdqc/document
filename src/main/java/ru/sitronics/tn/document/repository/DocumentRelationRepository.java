package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.DocumentRelation;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRelationRepository extends JpaRepository<DocumentRelation, String> {

    DocumentRelation findDocumentRelationByDocumentIdAndLinkDocument(String documentId, String linkDocumentId);
}

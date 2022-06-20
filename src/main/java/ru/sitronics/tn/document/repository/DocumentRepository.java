package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String>,
        JpaSpecificationExecutor<Document> { }

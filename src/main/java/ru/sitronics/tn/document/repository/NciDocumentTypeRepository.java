package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.model.NciDocType;
import ru.sitronics.tn.document.model.NciDocumentType;

@Repository
public interface NciDocumentTypeRepository extends JpaRepository<NciDocType, String> {
}

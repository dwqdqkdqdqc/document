package ru.sitronics.tn.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sitronics.tn.document.model.TableEntityAttachment;

@Repository
public interface TableEntityAttachmentRepository extends JpaRepository<TableEntityAttachment, String> {
}

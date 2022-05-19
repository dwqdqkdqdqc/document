package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.repository.DocumentRepository;
import ru.sitronics.tn.document.util.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;

    public Document get(String id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found: id = " + id));
    }

    public List<Document> getAll() {
        return repository.findAll();
    }

    public Document createOrUpdate(Document document) {
        return repository.save(document);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<Document> getByQuery(Specification<Document> spec) {
        return repository.findAll(spec);
    }

    public List<Document> getSerialNumber(Long serialNumber) {
        return repository.findBySerialNumber(serialNumber);
    }
}

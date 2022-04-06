package ru.sitronics.tn.document.service;

import ru.sitronics.tn.document.model.BaseDocument;
import ru.sitronics.tn.document.repository.DocumentRepository;
import ru.sitronics.tn.document.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;

    public BaseDocument get(String id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Document not found: id = " + id));
    }

    public List<BaseDocument> getAll() {
        return repository.findAll();
    }

    public BaseDocument createOrUpdate(BaseDocument document) {
        return repository.save(document);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public BaseDocument getByNumber(long number){
        return repository.findBySerialNumber(number);
    }

    public List<BaseDocument> getBySerialNumberOrderByCreator(long number){
        return repository.findBySerialNumberOrderByCreator(number);
    }
    public List<BaseDocument> getBySerialNumberOrderByCurators(long number){
        return repository.findBySerialNumberOrderByCurators(number);
    }

    public List<BaseDocument> getByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate){
       return repository.findByCreationDateBetween(startDate, endDate);
    }

    public List<BaseDocument> getByCreationDateGreaterThanEqual(LocalDateTime startDate){
        return repository.findByCreationDateGreaterThanEqual(startDate);
    }

    public List<BaseDocument> getByCreationDateLessThanEqual(LocalDateTime startDate){
        return repository.findByCreationDateLessThanEqual(startDate);
    }
}

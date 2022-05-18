package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.repository.DocumentRepository;
import ru.sitronics.tn.document.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    @Value("${rsql.defaultSort}")
    private String defaultSort;

    @Value("${rsql.defaultPageSize}")
    private Integer defaultPageSize;

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

    public void updateType(String id, String type) {
        Document document = get(id);
        document.setType(type);
        repository.save(document);
    }


    public Map<String, Object> findAll(String filter, Integer page, Integer size, String sort) {
        Map<String, Object> responseEntity = new HashMap<>();
        Page<Document> documentPage;

        //pages start from 1 for user
        if (page == null || page < 1) {
            log.warn("Invalid page value (page = {}). Set default page value = 0", page);
            page = 0; //default page
        } else --page;

        if (sort == null || sort.isBlank()) {
            log.warn("Invalid sort value (sort = {}). Set default sort value = {}", sort, defaultSort);
            sort = defaultSort;    //default sort
        }
        if (size == null || size <= 0) {
            log.warn("Invalid size value (size = {}). Set default size  = {}", size, defaultPageSize);
            size = defaultPageSize;  //default size
        }

        responseEntity.put("filter", filter);
        responseEntity.put("sort", sort);
        responseEntity.put("page", page+1);
        responseEntity.put("elements on page", size);

        if (filter == null || filter.isBlank()) {
            responseEntity.put("filter", null);
            documentPage = repository.findAll(RSQLJPASupport.toSort(sort), PageRequest.of(page, size));
        } else {
            Specification<?> specification = RSQLJPASupport.toSpecification(filter)
                    .and(RSQLJPASupport.toSort(sort));

            //  https://www.baeldung.com/java-warning-unchecked-cast#:~:text=4.2.%20Suppress%20the%20%E2%80%9Cunchecked%E2%80%9D%20Warning
            @SuppressWarnings("unchecked")
            Page<Document> temp = repository
                    .findAll((Specification<Document>) specification, PageRequest.of(page, size));
            documentPage = temp;
        }
        responseEntity.put("total amount", documentPage.getTotalElements());
        responseEntity.put("pages", documentPage.getTotalPages());
        responseEntity.put("entity", documentPage.stream().toList());

        return responseEntity;
    }


/*    public Document getByRsql(long serialNumber){
        return repository.findByRsql(serialNumber);
    }*/
/*
    public List<Document> getSerialNumberContaining(long serialNumber) {
        return repository.findBySerialNumberContaining(serialNumber);
    }

    public List<Document> getBySerialNumberOrderByAuthor(long serialNumber){
        return repository.findBySerialNumberOrderByAuthor(serialNumber);
    }
    public List<Document> getBySerialNumberOrderByCurators(long serialNumber){
        return repository.findBySerialNumberOrderByCurators(serialNumber);
    }

    public List<Document> getByDateOfCreationBetween(LocalDateTime startDate, LocalDateTime endDate){
       return repository.findByDateOfCreationBetween(startDate, endDate);
    }

    public List<Document> getByDateOfCreationGreaterThanEqual(LocalDateTime startDate){
        return repository.findByDateOfCreationGreaterThanEqual(startDate);
    }

    public List<Document> getByDateOfCreationLessThanEqual(LocalDateTime startDate){
        return repository.findByDateOfCreationLessThanEqual(startDate);
    }*/
}

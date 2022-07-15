package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.repository.DocumentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestDocumentService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final DocumentRepository documentRepository;

    public List<Document> findAllDocuments() {

        return documentRepository.findAll();
    }



    public List<Document> findTo() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = builder.createTupleQuery();
        Root<Document> root = cq.from(Document.class);

        Path<Long> idPath = root.get("id");
        Path<String> namePath = root.get("name");

        cq.multiselect(namePath, idPath );


        cq.multiselect(root.get("id"));
        List<Tuple> resultList = entityManager.createQuery(cq).getResultList();



        return resultList;
    }


}



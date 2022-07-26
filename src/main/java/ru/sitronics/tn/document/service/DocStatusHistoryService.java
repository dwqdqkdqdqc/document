package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sitronics.tn.document.model.DocStatusHistory;
import ru.sitronics.tn.document.repository.DocStatusHistoryRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocStatusHistoryService {
    private final DocStatusHistoryRepository statusHistoryRepository;

    public DocStatusHistory addNewStatusHistory(String docId, String status, String createdBy) {
        DocStatusHistory docStatusHistory = new DocStatusHistory();
        docStatusHistory.setDocId(docId);
        docStatusHistory.setStatus(status);
        docStatusHistory.setCreatedBy(createdBy);
        return statusHistoryRepository.save(docStatusHistory);
    }
}

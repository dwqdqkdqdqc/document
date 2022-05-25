package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.document.model.NciDocType;
import ru.sitronics.tn.document.model.NciDocumentType;
import ru.sitronics.tn.document.repository.NciDocumentTypeRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NciDocumentTypeService {

    private final NciDocumentTypeRepository repository;

/*    public List<String> getAll() {
        List<NciDocumentType> nciDocumentTypes = repository.findAll();
        List<String> documentType = nciDocumentTypes.stream().map(NciDocumentType::getNameRus).toList();
        return documentType;
    }*/

    public List<NciDocType> getAll() {
        return repository.findAll();
    }
}

package ru.sitronics.tn.document.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sitronics.tn.document.dto.DocumentDto;
import ru.sitronics.tn.document.model.Document;

@RequiredArgsConstructor
@Component
public class DocumentMapper {

    private final ModelMapper modelMapper;


    public DocumentDto convertToDto(Document doc) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(doc, DocumentDto.class);
    }

    public Document convertToEntity(DocumentDto docDto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(docDto, Document.class);
    }

}

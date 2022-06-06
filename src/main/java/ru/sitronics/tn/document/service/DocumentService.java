package ru.sitronics.tn.document.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewModule;
import com.monitorjbl.json.Match;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.sitronics.tn.document.model.Contract;
import ru.sitronics.tn.document.model.Document;
import ru.sitronics.tn.document.model.NciDocumentType;
import ru.sitronics.tn.document.model.Waybill;
import ru.sitronics.tn.document.repository.DocumentRepository;
import ru.sitronics.tn.document.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {
    @Autowired
    EntityManager manager;

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
        String id = repository.save(document).getId();
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "returned id from DB is null");
        }
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't found doc with id " + id));
    }

    public void delete(String id) {
        repository.deleteById(id);
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
        responseEntity.put("page", page + 1);
        responseEntity.put("elementsOnPage", size);

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
        responseEntity.put("totalAmount", documentPage.getTotalElements());
        responseEntity.put("pages", documentPage.getTotalPages());
        responseEntity.put("entity", documentPage.stream().toList());

        return responseEntity;
    }


    public Map<String, Object> findAllFields(String filter, Integer page, Integer size, String sort, String fields) {
        String[] selectedFields = fields.split(", ");

        Map<String, List<String>> nameClassesWithSelectedFields = Arrays.stream(selectedFields)
                .filter(field -> Arrays.stream(NciDocumentType.NciDocumentTypeEnum.values())
                        .map(en -> en.name().toLowerCase()).toList()
                        .contains(field.split("\\.")[0]))
                .collect(Collectors.groupingBy(field -> field.split("\\.")[0],
                        mapping(field -> field.replaceFirst(field.split("\\.")[0] + "\\.", ""), toList())));

        Map<String, Object> response = findAll(filter, page, size, sort);
        List<Object> entities = new ArrayList<>();

        if (response.get("entity") instanceof List<?> list) {
            entities = list.stream().map(obj -> {
                if (obj instanceof Document doc) {
                    return doc;
                } else return null;
            }).filter(Objects::nonNull).collect(toList());
        }

        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTimeSerializer.INSTANCE);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JsonViewModule());
        mapper.registerModule(module);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        try {
            // https://github.com/monitorjbl/json-view
            StringBuilder json = new StringBuilder();
            List<Document> entitiesList = new ArrayList<>(entities.stream().map(Document.class::cast).toList());  //?
            List<String> entityTypes = entitiesList.stream().map(Document::getType).toList();
            Optional<Integer> optionalInteger;
            int index = -1;

            for (String type : entityTypes) {
                switch (type) {
                    case "WAYBILL" -> {
                        optionalInteger = entitiesList.stream()
                                .filter(entity -> "WAYBILL".equalsIgnoreCase(entity.getType()))
                                .map(entitiesList::indexOf).findFirst();
                        if (optionalInteger.isPresent()) index = optionalInteger.get();

                        json.append(mapper.writeValueAsString((JsonView.with(entitiesList.remove(index))
                                .onClass(Waybill.class, Match.match().exclude("*").include(selectedFields))
                                .onClass(Contract.class, Match.match().exclude("*")
                                        .include(nameClassesWithSelectedFields.entrySet().stream()
                                                .filter(f -> f.getKey().equalsIgnoreCase("contract"))
                                                .flatMap(f -> f.getValue().stream()).toList().toArray(new String[0])))
                                .onClass(ru.sitronics.tn.document.model.Specification.class, Match.match().exclude("*")
                                        .include(nameClassesWithSelectedFields.entrySet().stream()
                                                .filter(f -> f.getKey().equalsIgnoreCase("specification"))
                                                .flatMap(f -> f.getValue().stream()).toList().toArray(new String[0])))))).append(",");
                    }

                    case "CONTRACT" -> {
                        optionalInteger = entitiesList.stream()
                                .filter(entity -> "CONTRACT".equalsIgnoreCase(entity.getType()))
                                .map(entitiesList::indexOf).findFirst();
                        if (optionalInteger.isPresent()) index = optionalInteger.get();

                        json.append(mapper.writeValueAsString((JsonView.with(entitiesList.remove(index))
                                .onClass(Contract.class, Match.match().exclude("*").include(selectedFields))
                                .onClass(Waybill.class, Match.match().exclude("*")
                                        .include(nameClassesWithSelectedFields.entrySet().stream()
                                                .filter(f -> f.getKey().equalsIgnoreCase("waybill"))
                                                .flatMap(f -> f.getValue().stream()).toList().toArray(new String[0])))
                                .onClass(ru.sitronics.tn.document.model.Specification.class, Match.match().exclude("*")
                                        .include(nameClassesWithSelectedFields.entrySet().stream()
                                                .filter(f -> f.getKey().equalsIgnoreCase("specification"))
                                                .flatMap(f -> f.getValue().stream()).toList().toArray(new String[0])))))).append(",");
                    }
                    case "SPECIFICATION" -> {
                        optionalInteger = entitiesList.stream()
                                .filter(entity -> "SPECIFICATION".equalsIgnoreCase(entity.getType()))
                                .map(entitiesList::indexOf).findFirst();
                        if (optionalInteger.isPresent()) index = optionalInteger.get();

                        json.append(mapper.writeValueAsString((JsonView.with(entitiesList.remove(index))
                                .onClass(ru.sitronics.tn.document.model.Specification.class, Match.match().exclude("*").include(selectedFields))
                                .onClass(Waybill.class, Match.match().exclude("*")
                                        .include(nameClassesWithSelectedFields.entrySet().stream()
                                                .filter(f -> f.getKey().equalsIgnoreCase("waybill"))
                                                .flatMap(f -> f.getValue().stream()).toList().toArray(new String[0])))
                                .onClass(Contract.class, Match.match().exclude("*")
                                        .include(nameClassesWithSelectedFields.entrySet().stream()
                                                .filter(f -> f.getKey().equalsIgnoreCase("contract"))
                                                .flatMap(f -> f.getValue().stream()).toList().toArray(new String[0])))))).append(",");
                    }

                    default -> System.out.println("Ok");
                }
            }

            json = new StringBuilder("[" + json.toString().replaceFirst("},$", "}]\""));
            JsonNode node = mapper.readTree(json.toString());
            response.put("entity", node);

            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

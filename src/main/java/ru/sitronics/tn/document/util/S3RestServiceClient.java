package ru.sitronics.tn.document.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3RestServiceClient {

    @Value("${s3RestService.url}")
    private String s3RestServiceUri;

    @Value("${s3RestService.postMultipartFilesEndpoint}")
    private String postMultipartFilesEndpoint;
    private final RestTemplate restTemplate;

    public <T> List<T> postMultipartFiles(MultipartFile[] files, Class<T> responseClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = s3RestServiceUri + postMultipartFilesEndpoint;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        Arrays.stream(files).forEach(file -> body.add("files", file.getResource()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<>() {});

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                return objectMapper.readValue(response.getBody(),
                        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, responseClass));
            } catch (JsonProcessingException e) {
                log.error("Error while trying parse response from s3Servise. Error message: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        } else throw new ResponseStatusException(response.getStatusCode());
    }
}

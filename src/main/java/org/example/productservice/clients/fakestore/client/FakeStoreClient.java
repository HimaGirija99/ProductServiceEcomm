package org.example.productservice.clients.fakestore.client;

import com.example.eCommerceSite.clients.fakestore.Dto.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){

        this.restTemplateBuilder = restTemplateBuilder;
    }
    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }


}

package org.example.productservice.controllers;

import org.example.productservice.clients.fakestore.client.FakeStoreClient;
import org.example.productservice.models.Product;
import org.example.productservice.services.fakeStoreProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    @MockBean
    RestTemplateBuilder restTemplatebuilder;

    @Autowired
    @MockBean
    FakeStoreClient fakeStoreClient;


    @MockBean
    fakeStoreProductService productService;

    @Autowired
    ProductController productController;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    void test_whenGetProductIsCalled_ReturnProducts() {
        Product product = new Product();
        product.setId(2l);
        product.setTitle("test");
        when(productService.getSingleProduct(any(Long.class))).thenReturn(product);

        ResponseEntity<Product> productResponseEntity = productController.getSingleProduct(2l);

        assertNotNull(productResponseEntity);
        assertEquals("test", productResponseEntity.getBody().getTitle());
    }

    //@Test
    void test_whenGetProductIsCalled_ReturnException() {
        // RULE
        when(productService.getSingleProduct(any(Long.class)))
                .thenThrow(new RuntimeException("Something is wrong"));

        assertThrows(RuntimeException.class,
                () -> productController.getSingleProduct(2l));
    }

    @Test
    void test_productControllerCallsProdcutServiceWithSameId() {
        Long id = 2l;
        when(productService.getSingleProduct(id)).thenCallRealMethod();

        productController.getSingleProduct(id);

        verify(productService).getSingleProduct(idCaptor.capture());
        verify(productService, times(1)).getSingleProduct(any());

        assertEquals(id, idCaptor.getValue());
    }

//    @Test
//    void test_productControllerCallsProdcutServiceWithSameId2() {
//        Long id = 2l;
//        when(productService.getSingleProduct(id)).thenCallRealMethod();
//
//        productController.getSingleProduct(id);
//        assertEquals(id, idCaptor.getValue());
//
//
//        verify(productService).getSingleProduct(idCaptor.capture());
//        verify(productService, times(1)).getSingleProduct(any());
//
//    }
}

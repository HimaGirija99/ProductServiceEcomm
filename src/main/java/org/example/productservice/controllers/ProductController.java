package org.example.productservice.controllers;

import org.example.productservice.clients.fakestore.DummyStore.IClientProductDto;
import org.example.productservice.dtos.ProductDto;
import org.example.productservice.models.Categories;
import org.example.productservice.models.Product;
import org.example.productservice.security.TokenValidator;
import org.example.productservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")// This controller will always answer products
public class ProductController {


    IProductService productService;
    TokenValidator tokenValidator;

    public ProductController(IProductService productService, TokenValidator tokenValidator) {
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id")Long productId){
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "htaccess");
            Product product = this.productService.getSingleProduct(productId);
            if (productId < 1) {
                throw new IllegalArgumentException("Something went wrong");
            }
            //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers, HttpStatus.OK);
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            //ResponseEntity<Product> responseEntity1 = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error code
            System.out.println("product id should be greater than 1");
        }
        /*
         try {
//            JwtObject authTokenObj = null;
//            if(authToken != null) {
//                Optional<JwtObject> authObjectOptional = tokenValidator.validateToken(authToken);
//                if(authObjectOptional.isEmpty()) {
//                    // throw exception
//                }
//                authTokenObj = authObjectOptional.get();
//            }
//            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            headers.add("Accept", "application/json");
//            headers.add("Content-Type", "application/json");
//            headers.add("auth-token", "hey access");
            // Apply rule based user Roles
            // Product product = this.productService.getSingleProduct(productId, authTokenObj);
            Product product = this.productService.getSingleProduct(productId);
            if(productId < 1) {
                throw new IllegalArgumentException("Something went wrong");
            }
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            //ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error code
            throw e;
        }
         */
        return null;
    }
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody IClientProductDto productDto) {
        Product product = this.productService.addNewProduct((Product) productDto);
        //ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PutMapping("/{ProductId}")
    public String updateProduct(@PathVariable("ProductId") Long productId) {

        return "Updating product "+productId;
    }
    @PatchMapping("/{ProductId}")
    public Product patchProduct(@PathVariable("ProductId") Long productId, @RequestBody ProductDto productDto) {

        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Categories());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return this.productService.updateProduct(productId, product);
    }
    @DeleteMapping("/{ProductId}")
    public String deleteProduct(@PathVariable("ProductId") Long productId) {
        return "Deleting a product with id: " + productId;
    }
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Such toh phat hai"+e, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

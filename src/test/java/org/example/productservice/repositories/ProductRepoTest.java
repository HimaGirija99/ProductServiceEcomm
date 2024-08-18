package org.example.productservice.repositories;


import org.example.productservice.models.Categories;
import org.example.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    void saveProductsAndCategory() {
        // Create and save a new category
        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories = categoryRepo.save(categories);
        when(categoryRepo.findByName("Electronics")).thenReturn(Optional.of(new Categories()));

        // Create and save a product linked to the above category
        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        product.setCategory(categories);
        productRepo.save(product);

        // Retrieve category with ID 1, ensuring it exists
        Categories categories1 = categoryRepo.findById(1L);
        if (categories1 == null) {
            throw new RuntimeException("Category not found");
        }


        // Get product list and check for null or empty
        List<Product> productList = categories1.getProductList();
        if (productList != null && !productList.isEmpty()) {
            productList.forEach(p -> System.out.println(p.getTitle()));
        } else {
            System.out.println("No products found for this category");
        }

        System.out.println("Debug");
    }


    @Test
        @Transactional
    void saveProductsAndCategory2() {
        Categories categories = new Categories();
        categories.setName("Fashion");
        categories.setDescription("Fashion");
        categories = categoryRepo.save(categories);

        Product product = new Product();
        product.setTitle("Tshirt");
        product.setDescription("Tshirt");
        product.setCategory(categories);
        productRepo.save(product);

        Categories categories1 = categoryRepo.findById(categories.getId()).get();
        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }

    @Test
    @Transactional
    void saveProductsAndCategory1() {
        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        productRepo.save(product);

        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories.setProductList(List.of(product));
        categories = categoryRepo.save(categories);

        Categories categories1 = categoryRepo.findById(categories.getId()).get();
        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void saveProductsAndCategory3() {
        Categories category = categoryRepo.findById(2L);
        //List<Product> productList = category.getProductList();
//        for (Product product : productList) {
//            System.out.println(product.getPrice());
//        }
        System.out.println("Debug");

//        Product product = new Product();
//        product.setPrice(1012);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        Product savedProduct = productRepo.save(product);
//
//        product = new Product();
//        product.setPrice(103);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        productRepo.save(product);


    }

    @Test
    @Transactional
    @Rollback(value = false)
    void saveProductsAndCategory4() {
        Categories category = categoryRepo.findById(2L);

        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        List<Product> productList = category.getProductList();
        productList.forEach(product -> System.out.println(product.getPrice()));

        List<Product> products = (List<Product>) productRepo.findByPriceBetween(1000, 1012);
        if (!products.isEmpty()) {
            List<Product> allProducts = productRepo.findByIdIsNotNullOrderByPrice();
            String title = productRepo.findTitleById(252L);
            if (title != null) {
                System.out.println(title);
            } else {
                System.out.println("Title not found for the product");
            }
        }

        Product product = new Product();
        product.setPrice(1012);
        product.setImageUrl("hiii");
        product.setCategory(category);
        Product savedProduct = productRepo.save(product);

        product = new Product();
        product.setPrice(103);
        product.setImageUrl("hiii");
        product.setCategory(category);
        productRepo.save(product);
    }

}

package org.example.productservice.services;

import org.example.productservice.models.Product;
import org.example.productservice.repositories.ProductRepo;

import java.util.List;

public class SelfProductService implements IProductService{

        ProductRepo productRepo;

        public SelfProductService(ProductRepo productRepo) {
            this.productRepo = productRepo;
        }
        @Override
        public List<Product> getAllProducts() {
            return null;
        }

        @Override
        public Product getSingleProduct(Long productId) {
            return null;
        }

        @Override
        public Product addNewProduct(Product product) {
            this.productRepo.save(product);
            return product;
        }

        @Override
        public Product updateProduct(Long productId, Product product) {
            return null;
        }

        @Override
        public String deleteProduct(Long productId) {
            return null;
        }
}

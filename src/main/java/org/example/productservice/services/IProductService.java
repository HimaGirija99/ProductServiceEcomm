package org.example.productservice.services;

import org.example.productservice.models.Product;


import java.util.List;

public interface IProductService {


        List<Product> getAllProducts();

        Product getSingleProduct(Long productId);

        Product addNewProduct(Product product);



        Product updateProduct(Long productId, Product product);





        String deleteProduct(Long productId);


}

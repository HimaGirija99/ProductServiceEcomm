package org.example.productservice.repositories;


import org.example.productservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save( Product product); // (save) is a method of JpaRepository (interface)

    Product findProductById(Long Id);
    Product findByPriceBetween(double greaterThan, double lessThan);

    //Product findByProductName(String productName);

    //String findTitleById(Long id);
    List<Product> findByTitleEquals(String title, Pageable pageable);

    List<Product> findByIdIsNotNullOrderByPrice();

    List<Product> findAllByIsPublicFalse();

    String findTitleById(long l);
}

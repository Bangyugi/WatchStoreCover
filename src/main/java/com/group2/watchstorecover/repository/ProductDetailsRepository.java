package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Product;
import com.group2.watchstorecover.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails,Integer> {
    Optional<ProductDetails> findByProductDetailsId(int productDetailsId);

    Optional<ProductDetails> findByProduct(Product product);

    boolean existsByProduct(Product product);
}

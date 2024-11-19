package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByProductName(String productName);

    @Query (value = "from Product p join fetch p.brand where p.productAvailable = :b and p.productId = :productId")
    Optional<Product> findByProductIdAndProductAvailable(int productId, boolean b);

    @Query(value = "from Product p join fetch p.brand where p.productAvailable = :b")
    Page<Product> findAllByProductAvailable(boolean b, Pageable pageable);
}

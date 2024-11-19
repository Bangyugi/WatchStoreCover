package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.idclass.CategoryProduct;
import com.group2.watchstorecover.idclass.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryProductRepository extends JpaRepository<com.group2.watchstorecover.entity.CategoryProduct,CategoryProduct> {
   @Query(value = "FROM CategoryProduct cp WHERE cp.category.categoryId =:categoryId AND cp.product.productId=:productId")
    Optional<com.group2.watchstorecover.entity.CategoryProduct> findByProductIdAndCategoryId(int productId, int categoryId);
}

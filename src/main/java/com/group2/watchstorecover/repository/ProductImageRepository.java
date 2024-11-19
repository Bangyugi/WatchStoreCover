package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {

}

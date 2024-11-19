package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByBrandName(String brandName);

    Optional<Brand> findByBrandIdAndBrandAvailable(int brandId, boolean b);

    Page<Brand> findAllByBrandAvailable(boolean b, Pageable pageable);
}

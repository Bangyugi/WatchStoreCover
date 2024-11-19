package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findAllByCategoryAvailable(boolean b, Pageable pageable);

    Optional<Category> findByCategoryIdAndCategoryAvailable(int categoryId, boolean b);

    boolean existsCategoryByCategoryName(String categoryName);
}

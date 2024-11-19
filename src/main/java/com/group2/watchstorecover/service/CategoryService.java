package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.CategoryRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;


public interface CategoryService {
    Response findAll(Pageable pageable);

    Response findById(int categoryId);

    Response addCategory(CategoryRequest categoryRequest);

    Response updateCategory(int categoryId, CategoryRequest categoryRequest);

    Response deleteCategory(int categoryId);
}

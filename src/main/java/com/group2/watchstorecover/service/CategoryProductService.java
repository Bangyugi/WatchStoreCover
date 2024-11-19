package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.CategoryProductRequest;
import com.group2.watchstorecover.dto.response.Response;

public interface CategoryProductService {
    Response addCategoryProduct(CategoryProductRequest categoryProductRequest);

    Response deleteCategoryProduct(int productId, int categoryId);
}

package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.CategoryProductRequest;
import com.group2.watchstorecover.dto.response.Response;
import com.group2.watchstorecover.entity.Category;
import com.group2.watchstorecover.entity.CategoryProduct;
import com.group2.watchstorecover.entity.Product;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.repository.CategoryProductRepository;
import com.group2.watchstorecover.repository.CategoryRepository;
import com.group2.watchstorecover.repository.ProductRepository;
import com.group2.watchstorecover.service.CategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryProductServiceImpl implements CategoryProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryProductRepository categoryProductRepository;

    @Override
    public Response addCategoryProduct(CategoryProductRequest categoryProductRequest) {
        Product product =productRepository.findByProductIdAndProductAvailable(categoryProductRequest.getProductId(),true).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        List<Category> categories = categoryProductRequest.getCategoryIds().stream()
                .map(category-> categoryRepository.findByCategoryIdAndCategoryAvailable(category,true).orElseThrow(()-> new AppException(ErrorCode.CATEGORY_NOT_FOUND))).toList();

        List<CategoryProduct> categoryProducts = new ArrayList<>();
        for (Category category: categories){
            CategoryProduct categoryProduct = new CategoryProduct();

            categoryProduct.setCategory(category);
            categoryProduct.setProduct(product);
            categoryProductRepository.save(categoryProduct);
            categoryProducts.add(categoryProduct);
        }
        return Response.builder()
                .code(201)
                .message("Add category product successfully")
                .data(categoryProducts)
                .build();
    }

    @Override
    public Response deleteCategoryProduct (int categoryId, int productId){
        CategoryProduct categoryProduct = categoryProductRepository.findByProductIdAndCategoryId(productId,categoryId).orElseThrow(()-> new AppException(ErrorCode.ERR_ID_NOT_FOUND));
        categoryProductRepository.delete(categoryProduct);
        return Response.builder()
                .code(200)
                .message("Delete category product successfully")
                .build();
    }


}

package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.ProductRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Response addProduct(ProductRequest productRequest);

    Response updateProductById(int productId, ProductRequest productRequest);

    Response deleteProductById(int productId);

    Response findAllProducts(Pageable pageable);

    Response findProductById(int productId);

    Response findProductByProductName(String productName, Pageable pageable);
}

package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.ProductDetailsRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;

public interface ProductDetailsService {
    Response addProductDetails(ProductDetailsRequest productDetailsRequest);

    Response updateProductDetails(int productDetailsId, ProductDetailsRequest productDetailsRequest);

    Response getProductDetails(Pageable pageable);

    Response getProductDetailsById(int productDetailsId);

    Response getProductDetailsByProductId(int productId);
}

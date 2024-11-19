package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.BrandRequest;
import com.group2.watchstorecover.dto.response.Response;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    Response addBrand(BrandRequest brandRequest);

    Response updateBrand (int brandId, BrandRequest brandRequest);

    Response deleteBrand(int brandId);

    Response findAllBrand(Pageable pageable);

    Response findBrandById(int brandId);
}

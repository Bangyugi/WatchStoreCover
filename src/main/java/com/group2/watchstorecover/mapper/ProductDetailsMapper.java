package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.ProductDetailsRequest;
import com.group2.watchstorecover.entity.ProductDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductDetailsMapper {
    ProductDetails toProductDetails (ProductDetailsRequest productDetailsRequest);
    void updateProductDetails(@MappingTarget ProductDetails productDetails, ProductDetailsRequest productDetailsRequest);

}

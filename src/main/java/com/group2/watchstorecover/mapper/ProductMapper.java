package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.ProductRequest;
import com.group2.watchstorecover.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);
    void updateProduct(@MappingTarget Product product,ProductRequest productRequest);

}

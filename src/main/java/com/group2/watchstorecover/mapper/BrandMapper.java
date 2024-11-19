package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.BrandRequest;
import com.group2.watchstorecover.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toBrand(BrandRequest brandRequest);
}

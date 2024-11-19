package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.CategoryRequest;
import com.group2.watchstorecover.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);
}

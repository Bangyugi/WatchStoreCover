package com.group2.watchstorecover.mapper;

import com.group2.watchstorecover.dto.request.BlogRequest;
import com.group2.watchstorecover.entity.Blog;
import jakarta.validation.constraints.Size;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    Blog toBlog (BlogRequest blogRequest);
}

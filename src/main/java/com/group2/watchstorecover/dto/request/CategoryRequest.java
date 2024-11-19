package com.group2.watchstorecover.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CategoryRequest {
    @Size(min = 4, message = "Category name must have at least 4 character")
    String categoryName;
}

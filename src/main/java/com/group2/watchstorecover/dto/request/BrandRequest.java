package com.group2.watchstorecover.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandRequest {

    @Size(min = 4, message = "Brand name must have at least 4 character")
    String brandName;
    @Size(min = 100, message = "Brand details must have at least 100 character")
    String brandDetails;

}

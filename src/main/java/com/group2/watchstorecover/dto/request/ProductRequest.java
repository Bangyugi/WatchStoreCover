package com.group2.watchstorecover.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequest {

    @Size(min = 4, message = "Product name must have at least 4 character")
    String productName;
    @Min(value = 1, message = "Price must be at least 1")
    int productPrice;
    @Min(value = 1, message = "Quantity must be at least 1")
    int productQuantity;

    @Min(value = 0, message = "Price reduction must be at least 0")
    @Max(value = 100, message = "Price reduction must be at most 100")
    int productPriceReduction;

    int brandId;
}

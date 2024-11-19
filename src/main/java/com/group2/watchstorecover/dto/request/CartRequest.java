package com.group2.watchstorecover.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {

    int customerId;
    int productId;

    @Builder.Default
    @Min(value = 1, message = "Quantity must be at least 1")
    int quantity = 1;

    @Builder.Default
    @Column(columnDefinition = "DATE")
    LocalDate cartDate = LocalDate.now();
}

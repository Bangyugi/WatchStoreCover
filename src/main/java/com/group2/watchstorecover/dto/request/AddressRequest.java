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
public class AddressRequest {
    @Size(min = 1, message = "Commune is required")
    String addressCommune;
    @Size(min = 1, message = "District is required")
    String addressDistrict;
    @Size(min = 1, message = "Province is required")
    String addressProvince;
    String addressDetails;
    int customerId;
}

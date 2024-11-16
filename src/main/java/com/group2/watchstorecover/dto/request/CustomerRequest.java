package com.group2.watchstorecover.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequest {
    @Size(min = 2, message = "Customer name must have at least 2 character")
    String customerName;
    String customerEmail;
    @Size(min = 10, max = 11, message = "Phone number must have 10 or 11 digits")
    String customerPhone;
}

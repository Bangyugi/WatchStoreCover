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
public class AccountRequest {
    @Size(min=5, message = "Password must have at least 5 character")
    String username;
    @Size(min=8, message = "Password must have at least 8 character")
    String password;
    int customerId;
}

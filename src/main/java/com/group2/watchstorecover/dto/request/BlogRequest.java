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
public class BlogRequest {
    @Size(min = 10, message = "Blog name must have at least 10 character")
    String blogName;

    @Size(min=100, message = "Blog content must have at least 100 character")
    String blogContent;

    int customerId;
}

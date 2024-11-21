package com.group2.watchstorecover.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenResponse {
    String tokenContent ;
    String refreshToken ;
    int userId;
    String username ;
    Set<String> roleName ;
    Timestamp expToken ;
    Timestamp expRefreshToken;
}

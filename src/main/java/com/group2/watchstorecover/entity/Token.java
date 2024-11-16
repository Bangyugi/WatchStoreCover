package com.group2.watchstorecover.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Token {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tokenId;

    String tokenContent;
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime expirationToken;
    String refreshToken;
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime expirationRefreshToken;
    @ManyToOne @JoinColumn(name = "account_id")
    Account account;
}

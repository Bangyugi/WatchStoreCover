package com.group2.watchstorecover.service;

import com.group2.watchstorecover.dto.request.AuthenticationRequest;
import com.group2.watchstorecover.dto.response.TokenResponse;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    TokenResponse login(AuthenticationRequest authenticationRequest);

    @Transactional
    TokenResponse refreshToken(String refreshToken);
}

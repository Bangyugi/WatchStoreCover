package com.group2.watchstorecover.service.impl;

import com.group2.watchstorecover.dto.request.AuthenticationRequest;
import com.group2.watchstorecover.dto.response.TokenResponse;
import com.group2.watchstorecover.entity.Account;
import com.group2.watchstorecover.entity.Role;
import com.group2.watchstorecover.exception.AppException;
import com.group2.watchstorecover.exception.ErrorCode;
import com.group2.watchstorecover.repository.AccountRepository;
import com.group2.watchstorecover.service.AuthenticationService;
import com.group2.watchstorecover.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public TokenResponse login(AuthenticationRequest authenticationRequest){
        Account account = accountRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
        if (account == null){
            throw new AppException(ErrorCode.ACCOUNT_UNAUTHENTICATED);
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
        }catch (Exception e){
            throw new AppException(ErrorCode.ACCOUNT_UNAUTHENTICATED);
        }

        var token = jwtService.generateToken(account, 3600000L);
        var refreshToken = jwtService.generateToken(account, 7200000L);
        Set<String> roles = new HashSet<>();
        Role role = account.getRole();
        roles.add(role.getRoleName());
        return TokenResponse.builder()
                .tokenContent(token)
                .refreshToken(refreshToken)
                .userId(account.getAccountId())
                .username(authenticationRequest.getUsername())
                .roleName(roles)
                .expToken(new Timestamp(System.currentTimeMillis() + 3600000L))
                .expRefreshToken(new Timestamp(System.currentTimeMillis() + 7200000L))
                .build();

    }

    @Transactional
    @Override
    public TokenResponse refreshToken(String refreshToken) {
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }
        String username = jwtService.extractUsername(refreshToken);
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));

        var token = jwtService.generateToken(account, 3600000L);
        var newRefreshToken = jwtService.generateToken(account, 7200000L);
        Set<String> roles = new HashSet<>();
        Role role = account.getRole();
        roles.add(role.getRoleName());
        return TokenResponse.builder()
                .tokenContent(token)
                .refreshToken(newRefreshToken)
                .userId(account.getAccountId())
                .username(username)
                .roleName(roles)
                .expToken(new Timestamp(System.currentTimeMillis() + 3600000L))
                .expRefreshToken(new Timestamp(System.currentTimeMillis() + 7200000L))
                .build();
    }

}

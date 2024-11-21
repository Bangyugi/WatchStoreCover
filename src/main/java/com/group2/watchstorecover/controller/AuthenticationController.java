package com.group2.watchstorecover.controller;

import com.group2.watchstorecover.dto.request.AuthenticationRequest;
import com.group2.watchstorecover.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/refresh-token/{refreshToken}")
    @Operation(summary = "Refresh token")
    public ResponseEntity<?> refreshToken(@PathVariable String refreshToken){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}

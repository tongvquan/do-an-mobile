package org.example.doanmobile.controller;

import lombok.RequiredArgsConstructor;
import org.example.doanmobile.model.LoginRequest;
import org.example.doanmobile.model.LoginResponse;
import org.example.doanmobile.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getUserName(), request.getPassword());
    }
}

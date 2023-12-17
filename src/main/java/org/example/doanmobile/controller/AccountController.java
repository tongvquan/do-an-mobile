package org.example.doanmobile.controller;

import lombok.RequiredArgsConstructor;
import org.example.doanmobile.model.LoginDto;
import org.example.doanmobile.model.LoginResponsive;
import org.example.doanmobile.model.RegisterDto;
import org.example.doanmobile.service.AuthService;
import org.example.doanmobile.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountController {
    private final AuthService authService;

    private final UserService userService;

    @PostMapping("/auth/login")
    public LoginResponsive login(@RequestBody @Validated LoginDto request) {
        return authService.attemptLogin(request.getUserName(), request.getPassword());
    }

    @PostMapping("/register/account")
    public ResponseEntity<String> register(@RequestBody @Validated RegisterDto request) {
        if(userService.register(request) == true){
            return new ResponseEntity<>("Register successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to register account", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package org.example.doanmobile.service;

import lombok.RequiredArgsConstructor;
import org.example.doanmobile.model.LoginResponsive;
import org.example.doanmobile.security.JwtIssuer;
import org.example.doanmobile.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public LoginResponsive attemptLogin(String userName, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var token = jwtIssuer.issue(JwtIssuer.Request.builder()
                .userId(principal.getUserId())
                .userName(principal.getUsername())
                .roles(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build());

        return LoginResponsive.builder()
                .token(token)
                .build();
    }
}

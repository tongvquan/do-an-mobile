package org.example.doanmobile.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
    private final String userName;
    private final String password;
}

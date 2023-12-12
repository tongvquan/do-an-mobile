package org.example.doanmobile.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDto {
    private final String userName;
    private final String password;
}

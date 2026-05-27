package com.school.mgmt.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AuthTokenStore {

    private String accessToken;
    private String refreshToken;
    private String csrfToken;
}
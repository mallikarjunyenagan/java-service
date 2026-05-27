package com.school.mgmt.backend.service;


import com.school.mgmt.backend.config.AuthTokenStore;
import com.school.mgmt.backend.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestClient restClient;
    private final AuthTokenStore tokenStore;

    public String login() {

        LoginRequest request = new LoginRequest();

        request.setUsername("admin@school-admin.com");
        request.setPassword("3OU4zn3q6Zh9");

        var response = restClient.post()
                .uri("/api/v1/auth/login")
                .body(request)
                .retrieve()
                .toEntity(String.class);

        // Extract cookies
        List<String> cookies =
                response.getHeaders()
                        .get(HttpHeaders.SET_COOKIE);

        if (cookies != null) {
            extractCookies(cookies);
        }

        return response.getBody();
    }

    /**
     * Extract tokens from Set-Cookie
     */
    private void extractCookies(List<String> cookies) {

        for (String cookie : cookies) {

            String[] cookieParts =
                    cookie.split(";")[0].split("=");
            String name="";
            String value="";
            if (cookieParts.length >1) {
                 name = cookieParts[0];
                 value = cookieParts[1];
            }

            switch (name) {

                case "accessToken":
                    tokenStore.setAccessToken(value);
                    break;

                case "refreshToken":
                    tokenStore.setRefreshToken(value);
                    break;

                case "csrfToken":
                    tokenStore.setCsrfToken(value);
                    break;
            }
        }

    }
}

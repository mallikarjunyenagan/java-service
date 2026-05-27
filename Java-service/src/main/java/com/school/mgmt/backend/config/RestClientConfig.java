package com.school.mgmt.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    @Value("${app.external.base-url}")
    private String baseUrl;

    private final AuthTokenStore tokenStore;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor((request, body, execution) -> {

                    if (tokenStore.getAccessToken() != null) {
                        String cookieHeader =
                                "accessToken=" + tokenStore.getAccessToken()
                                        + "; refreshToken=" + tokenStore.getRefreshToken()
                                        + "; csrfToken=" + tokenStore.getCsrfToken();

                        request.getHeaders()
                                .add(HttpHeaders.COOKIE, cookieHeader);
                        request.getHeaders()
                                .add("x-csrf-token",
                                        tokenStore.getCsrfToken());
                    }
                    return execution.execute(request, body);
                }).build();
    }
}

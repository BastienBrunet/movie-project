package com.mouvie.client.config;

import com.mouvie.security.config.customizable.AbstractHttpSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class HttpSecurityConfig implements AbstractHttpSecurityConfig {

    @Override
    public AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry getSecurityPattern(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry requests) {
        return requests
                .anyRequest()
                .authenticated();
    }
}

package com.odestopolanco.odestopolancoblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Class for configuring the security filter chain bean
 */
@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(authz ->
                authz.requestMatchers(HttpMethod.GET, "/v1/posts").hasAnyAuthority("SCOPE_profile")
                        .anyRequest().authenticated()).oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> {
        }));
        return http.build();
    }
}
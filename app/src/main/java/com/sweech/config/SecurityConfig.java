package com.sweech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() 
            .authorizeRequests()
            .antMatchers("/", "/api/hello", "/api/users/register", "/api/auth/login", "/api/posts/**", "/api/comments/**", "/api/login-records/**", "/api/rankings/**").permitAll() // Allow unauthenticated access to these endpoints
            .anyRequest().authenticated(); 
        return http.build();
    }
}

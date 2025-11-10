package org.walkgo.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .csrf().disable()                 // disable CSRF for API clients (adjust if you use cookies)
          .authorizeHttpRequests(auth -> auth
              .anyRequest().permitAll()     // allow everyone
          );
        return http.build();
    }
}
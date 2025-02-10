package com.lm.service.install.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

  @Value("${management.endpoints.web.base-path:/actuator}")
  private String actuatorBasePath;

  @Bean
  public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {

    http
        .securityMatchers(requestMatcherConfigurer -> requestMatcherConfigurer
            .requestMatchers(GET, actuatorBasePath + "/**", "/ping"))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(requests -> requests
            .requestMatchers(GET, actuatorBasePath + "/**", "/ping").permitAll())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}

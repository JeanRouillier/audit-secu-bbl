package com.lm.service.install.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
/*
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
*/
}

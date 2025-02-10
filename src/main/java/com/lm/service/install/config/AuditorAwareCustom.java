package com.lm.service.install.config;

import lombok.AllArgsConstructor;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimAccessor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuditorAwareCustom implements AuditorAware<String>, DateTimeProvider {

  String auditorAware() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(Authentication::getPrincipal)
        .filter(Jwt.class::isInstance)
        .map(Jwt.class::cast)
        .map(JwtClaimAccessor::getSubject)
        .orElse(null);
  }

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(auditorAware());
  }

  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(ZonedDateTime.now());
  }

}

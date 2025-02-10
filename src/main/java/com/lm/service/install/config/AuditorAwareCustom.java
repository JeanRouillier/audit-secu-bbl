package com.lm.service.install.config;

import lombok.AllArgsConstructor;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuditorAwareCustom implements AuditorAware<String>, DateTimeProvider {

  Jwt auditorAware() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(Authentication::getPrincipal)
        .filter(Jwt.class::isInstance)
        .map(Jwt.class::cast)
        .orElse(null);
  }

  Optional<Jwt> getAuditor() {
    return Optional.ofNullable(auditorAware());
  }

  public String getSub() {
    return getAuditor()
        //Check Delegated access
        .map(jwt -> jwt.getClaimAsMap("act"))
        .map(actor -> (String) actor.get("sub"))
        //Get sub from Jwt root -> Member Access
        .orElse(getAuditor()
            .map(jwt -> jwt.getClaimAsString("sub"))
            .orElse(getClientId()));
  }

  private String getClientId() {
    return getAuditor()
        .map(jwt -> jwt.getClaimAsString("client_id"))
        .orElse(null);
  }

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(getSub());
  }

  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(ZonedDateTime.now());
  }

}

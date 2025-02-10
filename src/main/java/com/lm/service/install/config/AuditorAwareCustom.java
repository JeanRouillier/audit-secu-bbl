package com.lm.service.install.config;

import com.lm.service.install.security.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuditorAwareCustom implements AuditorAware<String>, DateTimeProvider {

  private AuthenticationFacade authenticationFacade;

  @Override
  public Optional<String> getCurrentAuditor() {
    return authenticationFacade.getAuthenticatedUserName();
  }

  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(ZonedDateTime.now());
  }
}

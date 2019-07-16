package com.lm.service.install.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A wrapper around {@link SecurityContextHolder} to get {@link Authentication#getPrincipal()}
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

  @Override
  public Optional<OpenIDUser> getAuthenticatedUser() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(Authentication::getPrincipal)
        .filter(OpenIDUser.class::isInstance)
        .map(principal -> (OpenIDUser) principal);
  }

  @Override
  public Optional<String> getAuthenticatedUserName() {
    return getAuthenticatedUser().map(OpenIDUser::getUserId);
  }
}

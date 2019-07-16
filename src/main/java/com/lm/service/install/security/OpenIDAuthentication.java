package com.lm.service.install.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class OpenIDAuthentication extends AbstractAuthenticationToken {

  private OpenIDUser openIDUser;

  public OpenIDAuthentication(OpenIDUser openIDUser) {
    super(Collections.emptyList());
    this.openIDUser = openIDUser;
    this.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return this.openIDUser;
  }
}

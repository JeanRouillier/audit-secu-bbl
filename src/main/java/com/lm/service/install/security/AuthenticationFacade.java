package com.lm.service.install.security;

import java.util.Optional;

/**
 * A wrapper to get the {@link OpenIDUser}
 */
public interface AuthenticationFacade {

  Optional<OpenIDUser> getAuthenticatedUser();

  Optional<String> getAuthenticatedUserName();
}

package com.lm.service.install.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class OpenIDAccessFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    SecurityContext securityContext = SecurityContextHolder.getContext();

    if (securityContext != null) {
      Optional<String> authHeader = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION));

      authHeader.ifPresent(s -> securityContext.setAuthentication(new OpenIDAuthentication(new
          OpenIDUser(s))));
      }

    filterChain.doFilter(request, response);
  }
}

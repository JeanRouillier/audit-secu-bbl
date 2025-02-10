package com.lm.service.install.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCConfig extends OncePerRequestFilter{

  public static final String TRACING_HEADER = "x-correlation-id";

  private static final String REQUEST_ID = "X-Request-Id";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String correlationId;
    var header = request.getHeader(TRACING_HEADER);
    var requestId = request.getHeader(REQUEST_ID);
    if (header != null) {
      try { // Http header sanitizing to avoid potential security breach
        correlationId = UUID.fromString(header).toString();
      } catch (IllegalArgumentException e) {
        correlationId = UUID.randomUUID().toString();
      }
    } else {
      correlationId = UUID.randomUUID().toString();
    }
    MDC.put(TRACING_HEADER, correlationId);
    response.setHeader(TRACING_HEADER, correlationId);
    response.setHeader(REQUEST_ID, requestId);
    filterChain.doFilter(request, response);
  }
}

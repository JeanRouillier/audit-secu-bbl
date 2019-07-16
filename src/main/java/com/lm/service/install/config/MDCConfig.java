package com.lm.service.install.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class MDCConfig extends OncePerRequestFilter{

  public static final String TRACING_ID = "x-correlation-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UUID correlationId = UUID.randomUUID();
        MDC.put(TRACING_ID, correlationId.toString());
        response.addHeader(TRACING_ID, correlationId.toString());

        filterChain.doFilter(request, response);
    }
}

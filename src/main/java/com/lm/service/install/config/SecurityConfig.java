package com.lm.service.install.config;

import com.lm.service.install.security.OpenIDAccessFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${app.security.enable:false}")
  private boolean enableSecurity;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .httpBasic().disable()
        .formLogin().disable()
        .csrf().disable()
        .logout().disable()
        .headers().frameOptions().deny();

      http.requestMatchers().antMatchers("/api/v1/**", "/api/internals/v1/**")
          .and().authorizeRequests().anyRequest().authenticated()
          .and().addFilterAfter(new OpenIDAccessFilter(), SecurityContextPersistenceFilter.class);

  }
}

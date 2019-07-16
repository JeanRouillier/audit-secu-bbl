package com.lm.service.install.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "auditorProvider")
public class PersistenceConfig {

  private AuditorAwareCustom auditorAwareCustom;

  @Bean
  AuditorAware<String> auditorProvider() {
    return auditorAwareCustom;
  }
}

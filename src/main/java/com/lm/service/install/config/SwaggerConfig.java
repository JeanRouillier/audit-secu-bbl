package com.lm.service.install.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private String swaggerClientId = "titi";

  private String swaggerSecretId = "toto";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .directModelSubstitute(LocalDateTime.class, String.class)
        .directModelSubstitute(LocalDate.class, String.class)
        .directModelSubstitute(LocalTime.class, String.class)
        .directModelSubstitute(ZonedDateTime.class, String.class)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.lm.service.install.controller"))
        .build()
        .securitySchemes(Arrays.asList(manualScheme()))
        .securityContexts(Collections.singletonList(securityContext()));
  }

  private ApiKey manualScheme() {
    return new ApiKey("ManualWay", HttpHeaders.AUTHORIZATION, "header");
  }


  @Bean
  public SecurityConfiguration security() {
    return SecurityConfigurationBuilder.builder()
        .clientId(this.swaggerClientId)
        .clientSecret(this.swaggerSecretId)
        .scopeSeparator(" ")
        .build();
  }

  private AuthorizationScope[] scopes() {
    return new AuthorizationScope[]{
        new AuthorizationScope("openid", "fetch id_token"),
        new AuthorizationScope("email", "fetch email"),
        new AuthorizationScope("advprofile", "refer to a lm user"),
        new AuthorizationScope("instala", "refer to a partner")
    };
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(
            Arrays.asList(
                new SecurityReference("openID", scopes()),
                new SecurityReference("ManualWay", new AuthorizationScope[]{})
            ))
        .build();
  }
}

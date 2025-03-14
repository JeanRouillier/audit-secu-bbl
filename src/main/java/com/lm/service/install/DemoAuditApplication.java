package com.lm.service.install;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class DemoAuditApplication {

	public static void main(String[] args) {
    SpringApplication.run(DemoAuditApplication.class, args);
	}

}

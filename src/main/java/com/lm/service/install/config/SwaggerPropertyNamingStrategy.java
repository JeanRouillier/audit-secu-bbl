package com.lm.service.install.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.configuration.ObjectMapperConfigured;

@Configuration
public class SwaggerPropertyNamingStrategy implements ApplicationListener<ObjectMapperConfigured> {

    @Override
    public void onApplicationEvent(ObjectMapperConfigured objectMapperConfigured) {
        objectMapperConfigured.getObjectMapper().setDateFormat(new StdDateFormat());
        objectMapperConfigured.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }
}

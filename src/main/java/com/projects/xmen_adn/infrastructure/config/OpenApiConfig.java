package com.projects.xmen_adn.infrastructure.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "XMEN-ADN", version = "0.0.1", description = "Aplicacion para deteccion de Mutantes"))
public class OpenApiConfig {

}

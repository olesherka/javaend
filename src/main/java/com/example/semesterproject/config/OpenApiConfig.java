package com.example.semesterproject.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI semesterProjectOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Semester Project API")
                        .description("Student, Teacher, Classroom management API")
                        .version("1.0.0")
                        .contact(new Contact().name("Alisher Ablekimov").email("smoke.paladin@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Health endpoint")
                        .url("/actuator/health"));
    }
}

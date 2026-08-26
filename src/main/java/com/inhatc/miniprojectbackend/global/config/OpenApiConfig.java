package com.inhatc.miniprojectbackend.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI miniProjectOpenAPI() {
        Info info = new Info()
                .title("Mini Project Backend API")
                .version("v0.0.1")
                .description("음식 주문 미니 프로젝트 백엔드 REST API 문서")
                .contact(new Contact()
                        .name("INHATC Mini Project Team"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI().info(info);
    }
}

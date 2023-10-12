package com.together.springStudy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.ast.OpNE;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi clubAPI(){
        return GroupedOpenApi.builder()
                .group("club")
                .pathsToMatch("/together/club/**")
                .build();
    }
    @Bean
    public OpenAPI togetherAPI(){
        Info info = new Info()
                .title("함께해요! Together API 명세서")
                .version("v2.0.1")
                .description("TEST 및 API 명세서로 사용할 예정");
        return new OpenAPI()
                .info(info);
    }
}

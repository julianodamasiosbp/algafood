package com.acme.algafood.core.springdoc;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI().info(new Info()
                .title("AlgaFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.springdoc.com")));
    }

/*     @Bean
    public GroupedOpenApi publicApiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/v1/**")
                .addOpenApiCustomiser(openApi -> {
                    openApi.info(new Info()
                .title("AlgaFood API - Versão 01")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.springdoc.com")));
                })
                .build();
    } */

/*     @Bean
    public GroupedOpenApi publicApiV2() {
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/v2/**")
                .addOpenApiCustomiser(openApi -> {
                    openApi.info(new Info()
                .title("AlgaFood API - Versão 02")
                .description("API aberta para clientes e restaurantes")
                .version("2")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.springdoc.com")));
                })
                .build();
    } */

}

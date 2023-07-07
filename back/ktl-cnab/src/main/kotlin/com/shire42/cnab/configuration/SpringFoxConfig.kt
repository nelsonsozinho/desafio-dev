package com.shire42.cnab.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SpringFoxConfig {

    @Bean
    fun apiDocumentationConfig(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.shire42.cnab.controller"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfo(
            "CNAB REST APIs",
            "Service to receive ad process transactions from the files",
            "1",
            "Terms of service",
             Contact("Nelson Sozinho", "", "ramesh@gmail.com"),
        "Apache 2.0",
        "https://www.apache.org/licenses/LICENSE-2.0.html",
        Collections.emptyList()
        )
    }

    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }
    private fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }
    private fun defaultAuth(): List<SecurityReference> {
        val scope = AuthorizationScope("global", "accessEverything")
        val authorizationScopesArrays = arrayOf(scope)
        val securityReference = SecurityReference("JWT", authorizationScopesArrays);
        return listOf(securityReference)
    }

}
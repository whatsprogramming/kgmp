package com.nhphuong.studentservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@OpenAPIDefinition(
        info = @Info(
                title = "Student Service API",
                description = "Student API Documentation",
                version = "1.0"

        ),
        security = @SecurityRequirement(name = "keycloak_oauth2"),
        servers = {
                @Server(url = "${server.servlet.context-path}",
                        description = "Default Server URL")
        }
)
@Configuration
public class SwaggerConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public OpenAPI customOpenAPI() {

        RestTemplate restTemplate = new RestTemplate();

        // Load discovery document
        String discoveryUrl = issuerUri + "/.well-known/openid-configuration";
        Map<String, Object> discovery = restTemplate.getForObject(discoveryUrl, Map.class);

        String authUrl = (String) discovery.get("authorization_endpoint");
        String tokenUrl = (String) discovery.get("token_endpoint");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("keycloak_oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(authUrl)
                                                .tokenUrl(tokenUrl)
                                                .scopes(new Scopes()
                                                        .addString("openid", "OpenID Connect")
                                                        .addString("profile", "User Profile"))))));
    }
}

package skops.company_team.skops_api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Skops API - Gestión de Usuarios")
                    .description("API para gestionar usuarios: registro, login, logout, obtención y actualización de datos.")
                    .version("1.0.0")
            )
            .addSecurityItem(SecurityRequirement().addList("BearerAuth"))
            .components(
                io.swagger.v3.oas.models.Components()
                    .addSecuritySchemes(
                        "BearerAuth",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
    }

    @Bean
    fun usuariosApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("Usuarios")
            .packagesToScan("skops.company_team.skops_api.usuario.controller")
            .build()
    }
}

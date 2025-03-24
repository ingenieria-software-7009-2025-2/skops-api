package skops.company_team.skops_api.usuario.controller.body

import io.swagger.v3.oas.annotations.media.Schema

data class UsuarioBody (

    @Schema(description = "Nombre de usuario", example = "Dua_Lupita54")
    val username: String = "",

    @Schema(description = "Correo electrónico del usuario", example = "correo@ejemplo.com")
    val mail: String = "",

    @Schema(description = "Contraseña del usuario", example = "contra525")
    val password: String = "",

    @Schema(hidden = true) // Oculta el campo en el Swagger
    val token: String = "",

    @Schema(hidden = true) // Oculta el campo en el Swagger
    val rol: String = ""
)
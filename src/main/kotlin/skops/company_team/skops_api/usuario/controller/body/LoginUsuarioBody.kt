package skops.company_team.skops_api.usuario.controller.body

import io.swagger.v3.oas.annotations.media.Schema

data class LoginUserBody(
    @Schema(description = "Correo electrónico del usuario", example = "correo@ejemplo.com")
    var mail: String,

    @Schema(description = "Contraseña del usuario", example = "contra525")
    var password: String)
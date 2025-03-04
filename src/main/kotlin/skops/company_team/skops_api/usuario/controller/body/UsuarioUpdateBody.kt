package skops.company_team.skops_api.usuario.controller.body

data class UsuarioUpdateBody(
    val mail: String? = null,
    val password: String? = null
)

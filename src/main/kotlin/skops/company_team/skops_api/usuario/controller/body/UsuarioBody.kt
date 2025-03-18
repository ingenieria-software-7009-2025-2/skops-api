package skops.company_team.skops_api.usuario.controller.body

data class UsuarioBody (val username: String = "",
                    val mail: String = "",
                    val password: String = "",
                    val token: String = "",
                    val rol: String = ""
)
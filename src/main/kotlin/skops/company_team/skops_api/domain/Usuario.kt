package skops.company_team.skops_api.domain

data class Usuario (var id_usuario: String?=null,
                    var mail: String,
                    var password: String,
                    var token: String?=null,
                    var rol: String="user"
)
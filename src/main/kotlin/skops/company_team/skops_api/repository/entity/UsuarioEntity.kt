package skops.company_team.skops_api.repository.entity

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class UsuarioEntity constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_usuario: Int =0,
    @Column(name = "email")
    var email: String ="",
    @Column(name = "contrasenia")
    var contrasenia: String="",
    @Column(name = "token")
    var token: String?=null
) {


}
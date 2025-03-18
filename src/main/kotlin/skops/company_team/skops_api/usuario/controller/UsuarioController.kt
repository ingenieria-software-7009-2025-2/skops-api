package skops.company_team.skops_api.usuario.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import skops.company_team.skops_api.domain.Usuario
import skops.company_team.skops_api.service.UsuarioService
import skops.company_team.skops_api.usuario.controller.body.LoginUserBody
import skops.company_team.skops_api.usuario.controller.body.UsuarioBody
import skops.company_team.skops_api.usuario.controller.body.UsuarioUpdateBody

@RestController
@RequestMapping("/v1/users")

class UsuarioController (var usuarioService: UsuarioService){

    @PostMapping
    fun agregarUsuario(@RequestBody usuarioBody: UsuarioBody): ResponseEntity<Any> {
        val miUsuario = Usuario(username = usuarioBody.username,
                                mail = usuarioBody.mail,
                                password = usuarioBody.password)
        val response = usuarioService.addUser(miUsuario)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login")
    fun createLogIn(@RequestBody loginUserBody: LoginUserBody): ResponseEntity<Usuario> {
        val result = usuarioService.login(loginUserBody.mail,loginUserBody.password)
        return if (result == null){
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(result)
        }
    }

    @PostMapping("/logout")
    fun createLogOut(@RequestHeader("Autorizacion") token:String): ResponseEntity<String>{
        val logout = usuarioService.logout(token)
        return if (!logout){
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.ok("Sesión Cerrada")
        }
    }

    @GetMapping("/me")
    fun meUsuario(@RequestHeader("Autorizacion") token:String): ResponseEntity<Usuario>{
        val response = usuarioService.getInfoAboutMe(token)
        return if (response != null){
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(401).build()
        }
    }

    @PutMapping("/me")
    fun actualizarUsuario(@RequestHeader("Autorizacion") token: String, @RequestBody usuarioUpdateBody: UsuarioUpdateBody): ResponseEntity<Usuario> {
        if (token == "") {
            return ResponseEntity.status(401).build()
        }
        val usuarioActualizado = usuarioService.updateUser(token, usuarioUpdateBody)
        return if (usuarioActualizado != null) {
            ResponseEntity.ok(usuarioActualizado)
        } else {
            ResponseEntity.status(401).build()
        }
    }

}
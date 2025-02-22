package skops.company_team.skops_api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")

class UsuarioController {

    @PostMapping("/usuario")
    fun createUsuario(@RequestBody usuarioBody: UsuarioBody): ResponseEntity<Usuario> {
        val miUsuario = Usuario(mail = usuarioBody.mail,
                                password = usuarioBody.password,
                                token = usuarioBody.token)

        return ResponseEntity.ok(miUsuario)
    }

    @PostMapping("/usuario/login")
    fun createLogIn(@RequestBody usuarioBody: UsuarioBody): ResponseEntity<Usuario> {
        val miUsuario = Usuario(mail = usuarioBody.mail,
            password = usuarioBody.password)

        return ResponseEntity.ok(miUsuario)
    }

    @PostMapping("/usuario/logout")
    fun createLogOut(): ResponseEntity<String>{
        return ResponseEntity.ok("sesion cerrada :D")
    }

    @GetMapping("/usuario/me")
    fun obtenerUsuario(): ResponseEntity<Usuario>{
        val miUsuario = Usuario(mail = "ejemplo@gmail.com", password = "12345", token = "token1")
        return ResponseEntity.ok(miUsuario)
    }

}
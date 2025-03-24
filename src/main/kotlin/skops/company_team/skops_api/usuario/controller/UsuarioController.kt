package skops.company_team.skops_api.usuario.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import skops.company_team.skops_api.domain.Usuario
import skops.company_team.skops_api.service.UsuarioService
import skops.company_team.skops_api.usuario.controller.body.LoginUserBody
import skops.company_team.skops_api.usuario.controller.body.UsuarioBody
import skops.company_team.skops_api.usuario.controller.body.UsuarioUpdateBody
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/v1/users")

class UsuarioController (var usuarioService: UsuarioService){

    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un usuario con username, correo y contraseña.")
    @PostMapping
    fun agregarUsuario(
        @io.swagger.v3.oas.annotations.parameters.RequestBody
            (description = "Cuerpo con la información del usuario a registrar", required = true)
        @RequestBody usuarioBody: UsuarioBody): ResponseEntity<Any> {

        val miUsuario = Usuario(username = usuarioBody.username,
                                mail = usuarioBody.mail,
                                password = usuarioBody.password)
        val response = usuarioService.addUser(miUsuario)
        return ResponseEntity.ok(response)
    }

    @Operation(summary = "Iniciar sesión", description = "Inicia sesión con correo y contraseña.")
    @PostMapping("/login")
    fun createLogIn(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cuerpo con email y contraseña para iniciar sesión", required = true)
        @RequestBody loginUserBody: LoginUserBody
    ): ResponseEntity<Usuario> {

        val result = usuarioService.login(loginUserBody.mail,loginUserBody.password)
        return if (result == null){
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(result)
        }
    }

    @Operation(summary = "Cerrar sesión", description = "Cierra la sesión del usuario con su token.", security = [SecurityRequirement(name = "BearerAuth")])
    @PostMapping("/logout")
    fun createLogOut(
        @Parameter(description = "Token generado para el usuario al iniciar sesión.")
        @RequestHeader("Autorizacion") token:String): ResponseEntity<String>{

        val logout = usuarioService.logout(token)
        return if (!logout){
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.ok("Sesión Cerrada")
        }
    }

    @Operation(summary = "Obtener información del usuario autenticado", security = [SecurityRequirement(name = "BearerAuth")])
    @GetMapping("/me")
    fun meUsuario(
        @Parameter(description = "Token generado para el usuario al iniciar sesión.")
        @RequestHeader("Autorizacion") authHeader:String): ResponseEntity<Usuario>{
        
        val token = authHeader.replace("Bearer ", "")
        val response = usuarioService.getInfoAboutMe(token)
        return if (response != null){
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(401).build()
        }
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos del usuario autenticado.", security = [SecurityRequirement(name = "BearerAuth")])
    @PutMapping("/me")
    fun actualizarUsuario(
        @Parameter(description = "Token generado para el usuario al iniciar sesión.")
        @RequestHeader("Autorizacion") token: String,
        @RequestBody usuarioUpdateBody: UsuarioUpdateBody): ResponseEntity<Usuario> {

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

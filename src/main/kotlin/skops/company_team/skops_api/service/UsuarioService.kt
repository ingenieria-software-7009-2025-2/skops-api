package skops.company_team.skops_api.service

import skops.company_team.skops_api.repository.UsuarioRepostory
import skops.company_team.skops_api.domain.Usuario
import org.springframework.stereotype.Service
import skops.company_team.skops_api.repository.entity.UsuarioEntity
import java.util.UUID



@Service
class UsuarioService(private var usuarioRepository: UsuarioRepostory) {


    fun addUser(usuario: Usuario): Usuario {

        //Convertimos el objeto del dominio al objeto que necesita nuestra BD
        val usuarioDB =
            UsuarioEntity(mail = usuario.mail, password = usuario.password!!, token = usuario.token)

        val result = usuarioRepository.save(usuarioDB)

        // Convertimos el objeto de nuestra BD a un objeto de nuestro dominio.
        val usuarioCreado = Usuario(
            id_usuario = result.id_usuario.toString(),
            mail = result.mail,
            token = result.token,
            password = result.password
        )
        return usuarioCreado
    }


    fun retrieveAllUser(): List<Usuario> {

        val myUsers = mutableListOf<Usuario>()

        val result = usuarioRepository.findAll()

        result.forEach { user: UsuarioEntity ->
            // Convertimos el objeto de nuestra BD a un objeto de nuestro dominio.
            val userFound = Usuario(
                id_usuario = user.id_usuario.toString(),
                mail = user.mail,
                token = user.token,
                password = user.password
            )

            myUsers.add(userFound)
        }

        return myUsers
    }

    fun login(mail: String, password: String): Usuario? {
        val userFound = usuarioRepository.findByEmailAndPassword(mail, password)

        return if (userFound != null) {
            val token = UUID.randomUUID().toString()
            updateTokenUser(userFound, token)
            Usuario(
                id_usuario = userFound.id_usuario.toString(),
                mail = userFound.mail,
                token = token,
                password = userFound.password,
            )
        } else {
            userFound
        }

    }

    fun updateTokenUser(user: UsuarioEntity, token: String) {
        user.token = token
        usuarioRepository.save(user)
    }


    fun logout(token: String): Boolean {
        val userFound = usuarioRepository.findByToken(token)

        if (userFound != null) {
            userFound.token = null
            usuarioRepository.save(userFound)
            return true
        } else return false
    }

    fun getInfoAboutMe(token: String): Usuario?{
        val userFound = usuarioRepository.findByToken(token)

        if (userFound != null) {
            return Usuario(
                id_usuario = userFound.id_usuario.toString(),
                mail = userFound.mail,
                token = "*******",
                password = userFound.password,
            )
        } else return null
    }
}
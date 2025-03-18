package skops.company_team.skops_api.service

import skops.company_team.skops_api.repository.UsuarioRepostory
import skops.company_team.skops_api.domain.Usuario
import org.springframework.stereotype.Service
import skops.company_team.skops_api.repository.entity.UsuarioEntity
import skops.company_team.skops_api.usuario.controller.body.UsuarioUpdateBody
import java.util.UUID



@Service
class UsuarioService(private var usuarioRepository: UsuarioRepostory) {


    fun addUser(usuario: Usuario): Usuario {

        //Convertimos el objeto del dominio al objeto que necesita nuestra BD
        val usuarioDB =
            UsuarioEntity(username = usuario.username, email = usuario.mail, contrasenia = usuario.password!!, token = usuario.token)

        val result = usuarioRepository.save(usuarioDB)

        // Convertimos el objeto de nuestra BD a un objeto de nuestro dominio.
        val usuarioCreado = Usuario(
            id_usuario = result.id_usuario.toString(),
            username = result.username,
            mail = result.email,
            token = result.token,
            password = result.contrasenia,
            rol = result.rol
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
                username = user.username,
                mail = user.email,
                token = user.token,
                password = user.contrasenia,
                rol = user.rol
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
                username = userFound.username,
                mail = userFound.email,
                token = token,
                password = userFound.contrasenia,
                rol = userFound.rol
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
                username = userFound.username,
                mail = userFound.email,
                token = "*******",
                password = userFound.contrasenia,
                rol = userFound.rol
            )
        } else return null
    }

    fun updateUser(token: String, updateBody: UsuarioUpdateBody): Usuario? {
        // Se busca el usuario por el token recibido
        val userEntity = usuarioRepository.findByToken(token) ?: return null

        // Actualiza los campos si se proporcionaron en el JSON
        updateBody.mail?.let { userEntity.email = it }
        updateBody.password?.let { userEntity.contrasenia = it }

        // Se guarda el usuario actualizado en la base de datos
        val updatedEntity = usuarioRepository.save(userEntity)

        // Se convierte la entidad actualizada al objeto del dominio y se retorna
        return Usuario(
            id_usuario = updatedEntity.id_usuario.toString(),
            username = updatedEntity.username,
            mail = updatedEntity.email,
            token = updatedEntity.token,
            password = updatedEntity.contrasenia,
            rol = updatedEntity.rol
        )
    }

}
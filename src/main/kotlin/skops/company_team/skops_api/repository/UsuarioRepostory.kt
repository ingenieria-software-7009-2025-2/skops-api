package skops.company_team.skops_api.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import skops.company_team.skops_api.repository.entity.UsuarioEntity

interface UsuarioRepostory : CrudRepository<UsuarioEntity, Int> {
    @Query(value = "SELECT * FROM usuario WHERE correo=?1", nativeQuery = true)
    fun findByEmail(email: String): UsuarioEntity?

    @Query(value = "SELECT * FROM usuario WHERE correo=?1 AND password=?2", nativeQuery = true)
    fun findByEmailAndPassword(email: String, password: String): UsuarioEntity?

    @Query(value = "SELECT * FROM usuario WHERE token=?1", nativeQuery = true)
    fun findByToken(token: String): UsuarioEntity?

}
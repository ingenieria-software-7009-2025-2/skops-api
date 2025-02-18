package skops.company_team.skops_api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/mascota")
class MascotaController {

    @GetMapping
    fun retrieveMascota(): ResponseEntity<Mascota> {

        val miMascota = Mascota(nombre = "Wip",
                                edad = "23",
                                raza = "Doberman")

        return ResponseEntity.ok(miMascota)
    }


    fun retrieveMascota2(): ResponseEntity<Mascota> {

        val miMascota = Mascota(nombre = "Wip",
                                edad = "23",
                                raza = "Doberman")

        return ResponseEntity.ok(miMascota)
    }

    @PostMapping
    fun createMascota(@RequestBody mascotaBody: MascotaBody): ResponseEntity<Mascota> {

        val miMascota = Mascota(nombre = mascotaBody.nombre,
                                edad = mascotaBody.edad,
                                raza = mascotaBody.raza)

        return ResponseEntity.ok(miMascota)
    }
}
package skops.company_team.skops_api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/health")
class HealthController {

    @GetMapping
    fun retrieveHealth(): ResponseEntity<String> {
        return ResponseEntity.ok("HOLA MUNDO :DDD")
    }

    @PostMapping
    fun retrieveHealth2(): ResponseEntity<Int> {
        return ResponseEntity.ok(2)
    }
}
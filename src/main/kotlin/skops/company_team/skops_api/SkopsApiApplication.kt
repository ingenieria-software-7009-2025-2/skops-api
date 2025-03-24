package skops.company_team.skops_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan(basePackages = ["skops.company_team.skops_api"])
class SkopsApiApplication

fun main(args: Array<String>) {
	runApplication<SkopsApiApplication>(*args)
}

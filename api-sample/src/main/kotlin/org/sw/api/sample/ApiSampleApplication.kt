package org.sw.api.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiSampleApplication

fun main(args: Array<String>) {
    runApplication<ApiSampleApplication>(*args)
}

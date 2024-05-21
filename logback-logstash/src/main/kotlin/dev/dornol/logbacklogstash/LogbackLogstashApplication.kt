package dev.dornol.logbacklogstash

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogbackLogstashApplication

fun main(args: Array<String>) {
    runApplication<LogbackLogstashApplication>(*args)
}

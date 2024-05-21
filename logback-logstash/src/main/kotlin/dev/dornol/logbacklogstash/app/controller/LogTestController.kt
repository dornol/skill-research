package dev.dornol.logbacklogstash.app.controller

import dev.dornol.logbacklogstash.app.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private val log = io.github.oshai.kotlinlogging.KotlinLogging.logger {}

@RestController
class LogTestController(
    private val bookRepository: BookRepository
) {
    private val logger = LoggerFactory.getLogger(LogTestController::class.java)

    @GetMapping
    fun logTest() {
        log.error { "KotlinLogging error test" }
        log.warn { "KotlinLogging warn test" }
        log.info { "KotlinLogging info test" }
        log.debug { "KotlinLogging debug test" }
        log.trace { "KotlinLogging trace test" }

        logger.error("logger error test")
        logger.warn("logger warn test")
        logger.info("logger info test")
        logger.debug("logger debug test")
        logger.trace("logger trace test")
    }

    @GetMapping("/error-test")
    fun errorTest() {
        try {
            throw IllegalStateException()
        } catch (e: Exception) {
            log.error(e) { "KotlinLogging error test" }
            logger.error("logger error test", e)
            e.printStackTrace()
        }
    }

    @GetMapping("/query")
    fun queryTest() {
        bookRepository.findAll()
    }

}
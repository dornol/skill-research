package com.neohoon.querydsl.app.task

import com.neohoon.querydsl.app.service.BookService
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class StartupTask(
    private val bookService: BookService
) : ApplicationListener<ApplicationStartedEvent> {
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        val searchBook = bookService.searchBooks()
        println("result: $searchBook")
    }
}
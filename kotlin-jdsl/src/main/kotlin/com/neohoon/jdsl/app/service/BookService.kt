package com.neohoon.jdsl.app.service

import com.neohoon.jdsl.app.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun searchBooks() = bookRepository.selectAll()


}
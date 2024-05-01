package com.neohoon.querydsl.app.service

import com.neohoon.querydsl.app.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun searchBooks() = bookRepository.selectAll()
}
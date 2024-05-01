package com.neohoon.querydsl.app.repository

import com.neohoon.querydsl.app.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>, BookQueryRepository {
}
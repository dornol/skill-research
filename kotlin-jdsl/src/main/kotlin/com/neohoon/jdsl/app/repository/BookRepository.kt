package com.neohoon.jdsl.app.repository

import com.neohoon.jdsl.app.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>, BookQueryRepository {
}
package com.neohoon.jdsl.app.repository

import com.neohoon.jdsl.app.domain.Book
import com.neohoon.jdsl.app.dto.BookDto
import com.neohoon.jdsl.app.dto.BookSearch
import org.springframework.data.domain.Pageable

interface BookQueryRepository {

    fun selectAll(): List<Book>

    fun selectAllByInlineView(): List<Long>

    fun selectProjectionAll(): List<BookDto>

    fun selectDynamic(search: BookSearch): List<Book>

    fun selectPaging(pageable: Pageable): List<Book>

}
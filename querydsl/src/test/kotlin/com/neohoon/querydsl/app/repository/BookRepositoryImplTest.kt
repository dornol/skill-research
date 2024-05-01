package com.neohoon.querydsl.app.repository

import com.neohoon.querydsl.app.domain.Book
import com.neohoon.querydsl.app.domain.Member
import com.neohoon.querydsl.app.dto.BookSearch
import com.neohoon.querydsl.config.JpaConfig
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Import(JpaConfig::class)
@Transactional
class BookRepositoryImplTest {

    @Autowired
    lateinit var em: EntityManager

    @Autowired
    lateinit var repository: BookRepository

    lateinit var members: List<Member>
    lateinit var books: List<Book>

    @BeforeEach
    fun setData() {
        members = (1..100).map { Member("김동혁($it)", 20 + it) }.also { it.forEach(em::persist) }
        books = (1..100).map { Book(name = "book($it)", author = members[it - 1]) }.also { it.forEach(em::persist) }
    }

    @Test
    fun `selectAll test`() {
        //given
        //when
        val result = repository.selectAll()

        //then
        assertEquals(books.size, result.size)
    }

    @Test
    fun `selectAllByInlineView test`() {
        //given
        //when
        val result = repository.selectAllByInlineView()

        //then
        println(result)
        assertEquals(books.size, result.size)
    }

    @Test
    fun `selectProjectionAll test`() {
        //given
        //when
        val result = repository.selectProjectionAll()

        //then
        println(result.size)
        assertEquals(books.filter { it.id!! >= 3 }.size, result.size)
    }

    @Test
    fun `selectDynamic test`() {
        //given
        val book = books[5]
        val search1 = BookSearch()
        val search2 = BookSearch(name = book.name.substring(2))
        val search3 = BookSearch(name = "", age = 50)
        val search4 = BookSearch(name = book.name, age = book.author.age)

        //when
        val result1 = repository.selectDynamic(search1)
        val result2 = repository.selectDynamic(search2)
        val result3 = repository.selectDynamic(search3)
        val result4 = repository.selectDynamic(search4)

        //then
        // result1
        assertEquals(books.size, result1.size)

        // result2
        assertEquals(1, result2.size)
        assertEquals(book.name, result2[0].name)

        // result3
        assertEquals(books.count { it.author.age >= search3.age!! }, result3.size)

        // result4
        assertEquals(1, result4.size)
        assertEquals(book, result4[0])
    }

    @Test
    fun `selectPaging test`() {
        //given
        val pageable = PageRequest.of(5, 10)    // offset 50, limit 10
        val offset = pageable.pageSize * pageable.pageNumber
        val limit = pageable.pageSize

        //when
        val result = repository.selectPaging(pageable)

        //then
        val expected = books.subList(offset, offset + limit)
        assertIterableEquals(expected, result)
    }

}

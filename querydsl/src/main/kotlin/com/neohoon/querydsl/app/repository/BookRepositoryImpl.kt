package com.neohoon.querydsl.app.repository

import com.neohoon.querydsl.app.domain.Book
import com.neohoon.querydsl.app.domain.QBook.book
import com.neohoon.querydsl.app.domain.QMember.member
import com.neohoon.querydsl.app.dto.BookDto
import com.neohoon.querydsl.app.dto.BookSearch
import com.neohoon.querydsl.app.dto.QBookDto
import com.querydsl.core.annotations.QueryProjection
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

@QueryProjection
data class DerivedEntity(
    val bookId: Long,
    val bookName: Long,
)

@Repository
class BookRepositoryImpl(
    private val query: JPAQueryFactory,
) : BookQueryRepository {
    override fun selectAll(): List<Book> {
        return query
            .select(book)
            .from(book)
            .orderBy(book.id.desc())
            .fetch()
    }

    override fun selectAllByInlineView(): List<Long> {
        TODO("Not yet implemented")
    }

    override fun selectProjectionAll(): List<BookDto> {
        return query
            .select(QBookDto(
                book.id,
                book.name,
                member.name
            ))
            .from(book)
            .join(book.author, member)
            .where(book.id.goe(3))
            .orderBy(book.id.desc())
            .fetch()
    }

    override fun selectDynamic(search: BookSearch): List<Book> {
        return query
            .select(book)
            .from(book)
            .join(member).on(book.author.eq(member))
            .where(
                nameLike(search.name),
                ageGoe(search.age)
            )
            .fetch()
    }

    override fun selectPaging(pageable: Pageable): List<Book> {
        return query
            .select(book)
            .from(book)
            .orderBy(book.id.asc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }

    private fun nameLike(name: String?) =
        name?.takeIf { StringUtils.hasText(name) }?.let { book.name.contains(name) }

    private fun ageGoe(age: Int?) =
        age?.let { member.age.goe(age) }

}
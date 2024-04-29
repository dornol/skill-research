package com.neohoon.jdsl.app.repository

import com.linecorp.kotlinjdsl.dsl.jpql.Jpql
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.hibernate.extension.createQuery
import com.neohoon.jdsl.app.domain.Book
import com.neohoon.jdsl.app.domain.Member
import com.neohoon.jdsl.app.dto.BookDto
import com.neohoon.jdsl.app.dto.BookSearch
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

data class DerivedEntity(
    val bookId: Long,
    val bookName: Long,
)

@Repository
class BookRepositoryImpl(
    private val em: EntityManager,
    private val context: JpqlRenderContext
) : BookQueryRepository {

    override fun selectAll() = jpql {
        select(
            entity(Book::class)
        ).from(
            entity(Book::class)
        ).orderBy(
            path(Book::id).desc()
        )
    }.let { em.createQuery(it, context).resultList }

    override fun selectAllByInlineView(): List<Long> {
        val subQuery = jpql {
            select<DerivedEntity>(
                path(Book::id).`as`(expression("bookId")), path(Book::name).`as`(expression("bookName"))
            ).from(entity(Book::class))
        }

        val query = jpql {
            select<Long>(
                path(DerivedEntity::bookId)
            ).from(
                subQuery.asEntity(),
            )
        }

        return em.createQuery(query, context).resultList
    }

    override fun selectProjectionAll() = jpql {
        selectNew<BookDto>(
            path(Book::id), path(Book::name), path(Member::name)
        ).from(
            entity(Book::class),
            join(Book::author)
        ).where(
            path(Book::id).greaterThanOrEqualTo(3L)
        ).orderBy(
            path(Book::id).desc()
        )
    }.let { em.createQuery(it, context).resultList }

    override fun selectDynamic(search: BookSearch) = jpql {
        select(
            entity(Book::class)
        )
            .from(
                entity(Book::class),
                join(Member::class).on(
                    path(Book::author)(Member::id).eq(path(Member::id))
                )
            )
            .where(
                and(
                    nameLike(search.name),
                    ageGoe(search.age)
                )
            )
    }.let { em.createQuery(it, context).resultList }

    override fun selectPaging(pageable: Pageable) = jpql {
        select(entity(Book::class))
            .from(entity(Book::class))
            .where(and())
            .orderBy(path(Book::id).asc())
    }.let {
        em.createQuery(it, context).apply {
            firstResult = pageable.pageNumber * pageable.pageSize
            maxResults = pageable.pageSize
        }.resultList
    }

    private fun Jpql.nameLike(name: String?) =
        name?.takeIf { StringUtils.hasText(it) }?.let { path(Book::name).like("%$name%") }

    private fun Jpql.ageGoe(age: Int?) =
        age?.let { path(Member::age).greaterThanOrEqualTo(age) }

}
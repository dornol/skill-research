package dev.dornol.flyway.app.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
class Book(
    title: String,
    pages: Int,
    author: Author,
    publisher: Publisher
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false)
    var pages: Int = pages
        protected set

    @CreatedDate
    var createdDate: LocalDateTime? = null
        protected set

    @LastModifiedDate
    var updatedDate: LocalDateTime? = null
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    var author: Author = author
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    var publisher: Publisher = publisher
        protected set

}
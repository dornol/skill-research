package dev.dornol.logbacklogstash.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "my_book")
open class Book (
    @Id
    @Column(name = "my_book_id")
    val id: Long,
    val name: String
) {
}
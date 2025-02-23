package dev.dornol.flyway.app.domain

import jakarta.persistence.*

@Entity
class Author(
    name: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(nullable = false)
    var name: String = name
        protected set

}
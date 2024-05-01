package com.neohoon.querydsl.app.domain

import jakarta.persistence.*

@Entity
@Table(name = "book")
class Book(
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    val author: Member
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, updatable = false)
    val id: Long? = null

}
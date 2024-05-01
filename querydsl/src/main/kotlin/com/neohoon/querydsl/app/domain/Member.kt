package com.neohoon.querydsl.app.domain

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    val name: String,
    val age: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, updatable = false)
    val id: Long? = null
}
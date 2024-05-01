package com.neohoon.querydsl.app.dto

import com.querydsl.core.annotations.QueryProjection

@QueryProjection
data class BookDto(
    val id: Long,
    val name: String,
    val authorName: String
)
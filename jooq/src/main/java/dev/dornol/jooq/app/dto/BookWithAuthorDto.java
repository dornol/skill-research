package dev.dornol.jooq.app.dto;

import java.time.LocalDateTime;

public record BookWithAuthorDto(
    long id,
    String title,
    LocalDateTime createdDate,
    Author author
) {}

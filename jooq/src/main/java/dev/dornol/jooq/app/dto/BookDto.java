package dev.dornol.jooq.app.dto;

import java.time.LocalDateTime;

public record BookDto(
        long id,
        String title,
        long authorId,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {}

package dev.dornol.jooq.app.dto;

public record BookInsertDto(
        String title,
        long authorId
) {
}

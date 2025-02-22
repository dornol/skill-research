package dev.dornol.jooq.app.dto;


import dev.dornol.jooq.app.dto.enums.SearchType;

public record SearchCondition(
        SearchType type,
        String text,
        int page,
        int pageSize
) {}

package dev.dornol.mybatis.app.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class BookWithAuthorDto {

    private Long id;
    private String title;
    private Author author;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}

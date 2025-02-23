package dev.dornol.mybatis.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public BookDto(String title, Long authorId) {
        this.title = title;
        this.authorId = authorId;
    }
}

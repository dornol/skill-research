package dev.dornol.mybatis.app.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class AuthorWithBooksDto {

    private long id;
    private String name;
    private List<BookDto> books;

}

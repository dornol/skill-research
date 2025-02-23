package dev.dornol.mybatis.app.repository;

import dev.dornol.mybatis.app.dto.BookDto;
import dev.dornol.mybatis.app.dto.BookWithAuthorDto;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MybatisTest
class BookMapperTest implements WithAssertions {
    private static final Logger log = LoggerFactory.getLogger(BookMapperTest.class);

    @Autowired
    BookMapper bookMapper;

    @Test
    void findAll() throws Exception {
        //given+

        //when
        List<BookDto> books = bookMapper.findAll();

        //then
        log.info("books: {}", books);
        assertThat(books).isNotEmpty();
        assertThat(books.stream().allMatch(book -> book.getId() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getTitle() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getAuthorId() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getCreatedDate() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getUpdatedDate() != null)).isTrue();
    }

    @Test
    void findById() throws Exception {
        //given
        long bookId = 1L;

        //when
        BookDto book = bookMapper.findById(bookId);

        //then
        log.info("book: {}", book);
        assertThat(book).isNotNull();
        assertThat(book.getId()).isEqualTo(bookId);
        assertThat(book.getTitle()).isNotNull();
        assertThat(book.getAuthorId()).isNotNull();
        assertThat(book.getCreatedDate()).isNotNull();
        assertThat(book.getUpdatedDate()).isNotNull();

    }

    @Test
    void findWithAuthorAll() throws Exception {
        //given


        //when
        List<BookWithAuthorDto> books = bookMapper.findWithAuthorAll();

        //then
        log.info("books: {}", books);
        assertThat(books).isNotEmpty();
        assertThat(books.stream().allMatch(book -> book.getId() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getTitle() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getCreatedDate() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getUpdatedDate() != null)).isTrue();

        assertThat(books.stream().allMatch(book -> book.getAuthor() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getAuthor().getId() != null)).isTrue();
        assertThat(books.stream().allMatch(book -> book.getAuthor().getName() != null)).isTrue();

    }

    @Test
    void save() throws Exception {
        //given
        BookDto newBook = new BookDto("new book", 1L);

        //when
        bookMapper.save(newBook);

        //then
        log.info("book: {}", newBook);
        assertThat(newBook.getId()).isNotNull();
    }


}
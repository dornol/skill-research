package dev.dornol.jooq.app.repository;

import dev.dornol.jooq.app.dto.BookDto;
import dev.dornol.jooq.app.dto.BookInsertDto;
import dev.dornol.jooq.app.dto.BookWithAuthorDto;
import dev.dornol.jooq.app.dto.SearchCondition;
import dev.dornol.jooq.app.dto.enums.SearchType;
import dev.dornol.jooq.domain.Tables;
import dev.dornol.jooq.domain.tables.records.AuthorRecord;
import dev.dornol.jooq.domain.tables.records.BookRecord;
import org.assertj.core.api.WithAssertions;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@JooqTest
class BookRepositoryTest implements WithAssertions {

    private static final Logger log = LoggerFactory.getLogger(BookRepositoryTest.class);

    BookRepository bookRepository;

    @Autowired
    DSLContext dsl;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository(dsl);
    }

    @Test
    void findById() throws Exception {
        //given
        long bookId = 1L;

        //when
        BookDto book = bookRepository.findById(bookId);

        //then
        log.info("bookDto: {}", book);

        assertThat(book).isNotNull();
        assertThat(book.id()).isEqualTo(bookId);
        assertThat(book.createdDate()).isNotNull();
    }

    @Test
    void findAll() throws Exception {
        //when
        List<BookDto> books = bookRepository.findAll();

        //then
        log.info("books: {}", books);
        assertThat(books).isNotNull();
        assertThat(books).isNotEmpty();
    }

    @Test
    void findBookWithAuthorById() throws Exception {
        //given
        long bookId = 1L;

        //when
        BookWithAuthorDto book = bookRepository.findBookWithAuthorById(bookId);

        //then
        log.info("book: {}", book);
        assertThat(book).isNotNull();
        assertThat(book.id()).isEqualTo(bookId);
    }

    @Test
    void findAllByCondition_noCondition() throws Exception {
        //given
        var condition = new SearchCondition(SearchType.ALL, "", 1, 100);

        //when
        List<BookWithAuthorDto> books = bookRepository.findAllByCondition(condition);

        //then
        log.info("books: {}", books);
        assertThat(books).isNotEmpty();
    }

    @Test
    void findAllByCondition_type_ALL() throws Exception {
        //given
        var condition = new SearchCondition(SearchType.ALL, "샘플", 1, 100);

        //when
        List<BookWithAuthorDto> books = bookRepository.findAllByCondition(condition);

        //then
        log.info("books: {}", books);
        assertThat(books).isNotEmpty();
        assertThat(books.stream().allMatch(book -> book.title().contains(condition.text()) || book.author().name().contains(condition.text()))).isTrue();
    }

    @Test
    void findAllByCondition_type_BOOKTITLE() throws Exception {
        //given
        var condition = new SearchCondition(SearchType.BOOK_TITLE, "샘플", 1, 100);

        //when
        List<BookWithAuthorDto> books = bookRepository.findAllByCondition(condition);

        //then
        log.info("books: {}", books);
        assertThat(books).isNotEmpty();
        assertThat(books.stream().allMatch(book -> book.title().contains(condition.text()))).isTrue();
    }

    @Test
    void findAllByCondition_type_AUTHORNAME() throws Exception {
        //given
        var condition = new SearchCondition(SearchType.AUTHOR_NAME, "샘플", 1, 100);

        //when
        List<BookWithAuthorDto> books = bookRepository.findAllByCondition(condition);

        //then
        log.info("books: {}", books);
        assertThat(books).isNotEmpty();
        assertThat(books.stream().allMatch(book -> book.author().name().contains(condition.text()))).isTrue();
    }

    @Test
    void save() throws Exception {
        //given
        Result<AuthorRecord> authors = dsl.fetch(Tables.AUTHOR);
        Result<BookRecord> books = dsl.fetch(Tables.BOOK);
        Long maxBookId = books.map(BookRecord::getId).stream().max(Long::compareTo).orElseThrow();

        BookInsertDto newBook = new BookInsertDto("inserted book", authors.getFirst().getId());

        //when
        long generatedId = bookRepository.save(newBook);

        //then
        log.info("generatedId: {}", generatedId);
        assertThat(generatedId).isGreaterThan(maxBookId);
    }


}
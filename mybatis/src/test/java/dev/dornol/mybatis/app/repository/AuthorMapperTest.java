package dev.dornol.mybatis.app.repository;

import dev.dornol.mybatis.app.dto.AuthorWithBooksDto;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class AuthorMapperTest implements WithAssertions {
    private static final Logger log = LoggerFactory.getLogger(AuthorMapperTest.class);

    @Autowired
    AuthorMapper authorMapper;

    @Test
    void findWithBooksById() throws Exception {
        //given
        long authorId = 1L;

        //when
        AuthorWithBooksDto author = authorMapper.findWithBooksById(authorId);

        //then
        assertThat(author).isNotNull();
        assertThat(author.getId()).isEqualTo(authorId);
        assertThat(author.getName()).isNotNull();

        assertThat(author.getBooks()).isNotEmpty();
        assertThat(author.getBooks().stream().allMatch(book -> book.getAuthorId() == authorId)).isTrue();
        assertThat(author.getBooks().stream().allMatch(book -> book.getTitle() != null)).isTrue();
        assertThat(author.getBooks().stream().allMatch(book -> book.getCreatedDate() != null)).isTrue();
        assertThat(author.getBooks().stream().allMatch(book -> book.getUpdatedDate() != null)).isTrue();
        assertThat(author.getBooks().stream().allMatch(book -> book.getUpdatedDate() != null)).isTrue();
        log.info("Author with id: " + authorId + " is: " + author);
    }

}
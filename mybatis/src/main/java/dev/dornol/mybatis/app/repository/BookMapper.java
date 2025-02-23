package dev.dornol.mybatis.app.repository;

import dev.dornol.mybatis.app.dto.BookDto;
import dev.dornol.mybatis.app.dto.BookWithAuthorDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {

    List<BookDto> findAll();

    BookDto findById(long id);

    List<BookWithAuthorDto> findWithAuthorAll();

    List<BookDto> findByAuthorId(long id);

    void save(BookDto bookDto);

}

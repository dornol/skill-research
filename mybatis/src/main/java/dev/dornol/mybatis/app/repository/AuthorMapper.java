package dev.dornol.mybatis.app.repository;

import dev.dornol.mybatis.app.dto.AuthorWithBooksDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthorMapper {

    AuthorWithBooksDto findWithBooksById(long id);

}

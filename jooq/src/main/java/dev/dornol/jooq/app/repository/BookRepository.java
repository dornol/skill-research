package dev.dornol.jooq.app.repository;

import dev.dornol.jooq.app.dto.*;
import dev.dornol.jooq.mytarget.tables.records.BookRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.TableField;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dev.dornol.jooq.mytarget.Tables.AUTHOR;
import static dev.dornol.jooq.mytarget.Tables.BOOK;

@Repository
@RequiredArgsConstructor
public class BookRepository implements JooqConditionSupportBase {
    private final DSLContext dsl;

    /**
     * 단건 조회
     *
     * @param id 도서 id
     * @return 도서정보
     */
    @Transactional(readOnly = true)
    public BookDto findById(long id) {
        return dsl.select()
                .from(BOOK)
                .where(BOOK.ID.eq(id))
                .fetchOneInto(BookDto.class);
    }

    /**
     * 여러건 조회
     *
     * @return 도서정보 리스트
     */
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return dsl.select()
                .from(BOOK)
                .orderBy(BOOK.CREATED_DATE.desc())
                .fetchInto(BookDto.class);
    }

    /**
     * 단건조회, Author join
     *
     * @param id 도서 id
     * @return 도서 정보 + 저자 정보
     */
    @Transactional(readOnly = true)
    public BookWithAuthorDto findBookWithAuthorById(long id) {
        return dsl.select()
                .from(BOOK)
                .innerJoin(BOOK.author())
                .where(BOOK.ID.eq(id))
                .fetchOne(bookWithAuthorDtoRecordMapper());
    }

    @Transactional(readOnly = true)
    public List<BookWithAuthorDto> findAllByCondition(SearchCondition condition) {

        List<TableField<? extends Record, String>> fields = switch (condition.type()) {
            case ALL -> List.of(BOOK.TITLE, AUTHOR.NAME);
            case BOOK_TITLE -> List.of(BOOK.TITLE);
            case AUTHOR_NAME -> List.of(AUTHOR.NAME);
        };

        return dsl.select()
                .from(BOOK)
                .innerJoin(AUTHOR)
                .on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
                .where(
                        contains(fields, condition.text())
                )
                .orderBy(BOOK.ID.desc())
                .offset((condition.page() - 1) * condition.pageSize())
                .limit(condition.pageSize())
                .fetch(bookWithAuthorDtoRecordMapper());
    }

    @Transactional
    public long save(BookInsertDto book) {
        BookRecord bookRecord = dsl.insertInto(BOOK, BOOK.TITLE, BOOK.AUTHOR_ID)
                .values(book.title(), book.authorId())
                .returning(BOOK.ID)
                .fetchOptional()
                .orElseThrow();
        return bookRecord.getId();
    }

    private RecordMapper<Record, BookWithAuthorDto> bookWithAuthorDtoRecordMapper() {
        return record -> new BookWithAuthorDto(
                record.get(BOOK.ID),
                record.get(BOOK.TITLE),
                record.get(BOOK.CREATED_DATE),
                new Author(
                        record.get(BOOK.author().ID),
                        record.get(BOOK.author().NAME)
                )
        );
    }

}

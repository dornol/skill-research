/*
 * This file is generated by jOOQ.
 */
package dev.dornol.jooq.domain.tables;


import dev.dornol.jooq.domain.Keys;
import dev.dornol.jooq.domain.Public;
import dev.dornol.jooq.domain.tables.Author.AuthorPath;
import dev.dornol.jooq.domain.tables.records.BookRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Book extends TableImpl<BookRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.book</code>
     */
    public static final Book BOOK = new Book();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookRecord> getRecordType() {
        return BookRecord.class;
    }

    /**
     * The column <code>public.book.id</code>.
     */
    public final TableField<BookRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.book.title</code>.
     */
    public final TableField<BookRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.book.author_id</code>.
     */
    public final TableField<BookRecord, Long> AUTHOR_ID = createField(DSL.name("author_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.book.created_date</code>.
     */
    public final TableField<BookRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.book.updated_date</code>.
     */
    public final TableField<BookRecord, LocalDateTime> UPDATED_DATE = createField(DSL.name("updated_date"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    private Book(Name alias, Table<BookRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Book(Name alias, Table<BookRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.book</code> table reference
     */
    public Book(String alias) {
        this(DSL.name(alias), BOOK);
    }

    /**
     * Create an aliased <code>public.book</code> table reference
     */
    public Book(Name alias) {
        this(alias, BOOK);
    }

    /**
     * Create a <code>public.book</code> table reference
     */
    public Book() {
        this(DSL.name("book"), null);
    }

    public <O extends Record> Book(Table<O> path, ForeignKey<O, BookRecord> childPath, InverseForeignKey<O, BookRecord> parentPath) {
        super(path, childPath, parentPath, BOOK);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BookPath extends Book implements Path<BookRecord> {
        public <O extends Record> BookPath(Table<O> path, ForeignKey<O, BookRecord> childPath, InverseForeignKey<O, BookRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BookPath(Name alias, Table<BookRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BookPath as(String alias) {
            return new BookPath(DSL.name(alias), this);
        }

        @Override
        public BookPath as(Name alias) {
            return new BookPath(alias, this);
        }

        @Override
        public BookPath as(Table<?> alias) {
            return new BookPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<BookRecord, Long> getIdentity() {
        return (Identity<BookRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<BookRecord> getPrimaryKey() {
        return Keys.BOOK_PKEY;
    }

    @Override
    public List<ForeignKey<BookRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BOOK__BOOK_AUTHOR_ID_FKEY);
    }

    private transient AuthorPath _author;

    /**
     * Get the implicit join path to the <code>public.author</code> table.
     */
    public AuthorPath author() {
        if (_author == null)
            _author = new AuthorPath(this, Keys.BOOK__BOOK_AUTHOR_ID_FKEY, null);

        return _author;
    }

    @Override
    public Book as(String alias) {
        return new Book(DSL.name(alias), this);
    }

    @Override
    public Book as(Name alias) {
        return new Book(alias, this);
    }

    @Override
    public Book as(Table<?> alias) {
        return new Book(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(String name) {
        return new Book(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(Name name) {
        return new Book(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(Table<?> name) {
        return new Book(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Book where(Condition condition) {
        return new Book(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Book where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Book where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Book where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Book where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Book where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Book where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Book where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Book whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Book whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}

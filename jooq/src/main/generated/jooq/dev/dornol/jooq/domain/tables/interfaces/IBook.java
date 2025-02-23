/*
 * This file is generated by jOOQ.
 */
package dev.dornol.jooq.domain.tables.interfaces;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IBook extends Serializable {

    /**
     * Getter for <code>public.book.id</code>.
     */
    public Long getId();

    /**
     * Getter for <code>public.book.title</code>.
     */
    public String getTitle();

    /**
     * Getter for <code>public.book.author_id</code>.
     */
    public Long getAuthorId();

    /**
     * Getter for <code>public.book.created_date</code>.
     */
    public LocalDateTime getCreatedDate();

    /**
     * Getter for <code>public.book.updated_date</code>.
     */
    public LocalDateTime getUpdatedDate();
}

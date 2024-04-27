package com.neohoon.enverstest.app.repository;

import com.neohoon.enverstest.app.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

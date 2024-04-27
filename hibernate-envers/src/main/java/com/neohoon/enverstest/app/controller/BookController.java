package com.neohoon.enverstest.app.controller;

import com.neohoon.enverstest.app.domain.Book;
import com.neohoon.enverstest.app.dto.BookSaveDto;
import com.neohoon.enverstest.app.repository.BookRepository;
import com.neohoon.enverstest.app.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @GetMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book book(@PathVariable long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public void saveBook(@RequestBody BookSaveDto book) {
        bookRepository.save(
                new Book(book.getBookName(), memberRepository.findById(book.getMemberId()).orElseThrow())
        );
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookRepository.findById(id).orElseThrow().delete();
    }

}

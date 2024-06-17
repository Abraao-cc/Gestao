package com.timuila.gestao.controlle;

import com.timuila.gestao.dominio.Book;
import com.timuila.gestao.dtos.BookRecord;
import com.timuila.gestao.services.BookService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrativo
 */
@RestController
@RequestMapping("/bookstore/books")
public class BookControlle {

    private final BookService bookService;

    public BookControlle(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Book> saveBook(@RequestBody BookRecord bookrecord) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookrecord));
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks());
    }

}

package com.timuila.gestao.services;

import com.timuila.gestao.dominio.Book;
import com.timuila.gestao.dtos.BookRecord;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface BookService {

    Book save(BookRecord book);

    Book update(Book book);

    void delete(UUID id);

    List<Book> getBooks();
}

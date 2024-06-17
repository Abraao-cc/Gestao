package com.timuila.gestao.services.imp;

import com.timuila.gestao.dominio.Book;
import com.timuila.gestao.dominio.Review;
import com.timuila.gestao.dtos.BookRecord;
import com.timuila.gestao.repositorys.AutorRepository;
import com.timuila.gestao.repositorys.BookRepository;
import com.timuila.gestao.repositorys.PublisherRepository;
import com.timuila.gestao.services.BookService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrativo
 */
@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    private final AutorRepository authorRepository;
    // private final ReviewRepository reviewRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImp(BookRepository bookRepository, AutorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

        this.publisherRepository = publisherRepository;
    }
@Transactional
    @Override
    public Book save(BookRecord bookRecord) {
        Book book = new Book();
        book.setTitle(bookRecord.title());
        book.setPublisher(publisherRepository.findById(bookRecord.publisher()).get());
        book.setAutors(authorRepository.findAllById(bookRecord.authors()).stream().collect(Collectors.toSet()));
        Review review = new Review();
        review.setComment(bookRecord.reviewComment());
        review.setBook(book);
        book.setReview(review);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Book> getBooks() {
      return  bookRepository.findAll(Sort.by("id"));
    }

}

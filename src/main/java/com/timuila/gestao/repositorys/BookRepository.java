package com.timuila.gestao.repositorys;

import com.timuila.gestao.dominio.Book;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrativo
 */
@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Book findBookByTitle(String title);

    @Query(value = "SELECT * FROM tb_books WHERE publisher_id = :id", nativeQuery = true)
    List<Book> findBooksByPublisherId(@Param("id") UUID id);
}

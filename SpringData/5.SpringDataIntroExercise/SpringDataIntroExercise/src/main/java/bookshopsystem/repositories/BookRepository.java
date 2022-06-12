package bookshopsystem.repositories;

import bookshopsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByReleaseDateAfter(LocalDate date);
}

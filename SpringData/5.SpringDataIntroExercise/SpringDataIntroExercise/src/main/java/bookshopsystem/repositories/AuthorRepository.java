package bookshopsystem.repositories;

import bookshopsystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByBooksReleaseDateBefore(LocalDate date);
    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllOrderedByBooksCount();

}

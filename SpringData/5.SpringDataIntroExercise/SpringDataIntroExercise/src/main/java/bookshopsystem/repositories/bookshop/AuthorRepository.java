package bookshopsystem.repositories.bookshop;

import bookshopsystem.models.bookshop.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    @Query("SELECT a FROM Author a WHERE a = (SELECT b.author FROM Book b WHERE b.releaseDate < :date)")
    List<Author> findAllByBooksReleaseDateBefore(LocalDate date);
    @Query("SELECT a FROM Author a ORDER BY size(a.books) DESC")
    List<Author> findAllOrderedByBooksCount();

}

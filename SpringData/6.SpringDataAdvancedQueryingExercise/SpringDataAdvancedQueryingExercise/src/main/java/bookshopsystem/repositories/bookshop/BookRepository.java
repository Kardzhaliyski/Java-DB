package bookshopsystem.repositories.bookshop;

import bookshopsystem.models.bookshop.Book;
import bookshopsystem.models.bookshop.enums.AgeRestriction;
import bookshopsystem.models.bookshop.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByReleaseDateAfter(LocalDate date);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> getBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> getBooksByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal lessThan, BigDecimal moreThan);

    List<Book> getBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> getBooksByReleaseDateBefore(LocalDate date);

    List<Book> getBooksByTitleContaining(String value);

    List<Book> getBooksByAuthorLastNameStartingWith(String value);

    @Query("SELECT count(b) FROM Book b where length(b.title) > :value ")
    int countBooksByTitleLengthGreaterThan(int value);

//
}

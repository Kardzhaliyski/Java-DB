package bookshopsystem.repositories.bookshop;

import bookshopsystem.models.bookshop.Book;
import bookshopsystem.models.bookshop.enums.AgeRestriction;
import bookshopsystem.models.bookshop.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByReleaseDateAfter(LocalDate date);

    List<Book> getBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> getBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> getBooksByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal lessThan, BigDecimal moreThan);

    List<Book> getBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> getBooksByReleaseDateBefore(LocalDate date);

    List<Book> getBooksByTitleContaining(String value);

    List<Book> getBooksByAuthorLastNameStartingWith(String value);

    @Query("SELECT count(b) FROM Book b where length(b.title) > :value ")
    int countBooksByTitleLengthGreaterThan(int value);

    @Query("SELECT b.title, b.editionType, b.ageRestriction, b.price FROM Book b WHERE b.title = :title")
    String getBookInfo(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies + :value WHERE b.releaseDate > :date")
    int increaseCopiesOfBooksReleasedAfter(LocalDate date,int value);

    @Transactional
    int deleteBooksByCopiesLessThan(int value);
}

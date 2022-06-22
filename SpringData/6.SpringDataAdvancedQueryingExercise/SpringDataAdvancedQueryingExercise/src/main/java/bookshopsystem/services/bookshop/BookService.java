package bookshopsystem.services.bookshop;

import bookshopsystem.models.bookshop.Book;
import bookshopsystem.models.bookshop.enums.AgeRestriction;
import bookshopsystem.models.bookshop.enums.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedDatabase(Path path) throws IOException;
    List<Book> getBooksReleasedAfter(int year);
    List<Book>getBooksFrom(String firstName, String lastName);

    List<Book> getBooksBy(AgeRestriction ageRestriction);

    List<Book> getBooksByEditionAndNumberOfCopiesLowerThan(EditionType type, int copies);

    List<Book> getBooksWithPriceNotBetween(BigDecimal lessThan, BigDecimal moreThan);

    List<Book> getBooksNotReleasedDuringYear(int year);

    List<Book> getBooksReleasedBefore(LocalDate date);

    List<Book> getBooksTitleContaining(String value);

    List<Book> getBooksByAuthorLastNameStartsWith(String value);

    int getCountOfBooksWithTitleLongerThan(int value);

    String getBookInfo(String title);

    int increaseCopiesOfBooksReleasedAfter(LocalDate date, int copies);
}

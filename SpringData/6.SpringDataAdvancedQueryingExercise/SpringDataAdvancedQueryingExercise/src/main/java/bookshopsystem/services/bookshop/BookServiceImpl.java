package bookshopsystem.services.bookshop;

import bookshopsystem.models.bookshop.Author;
import bookshopsystem.models.bookshop.Book;
import bookshopsystem.models.bookshop.Category;
import bookshopsystem.models.bookshop.enums.AgeRestriction;
import bookshopsystem.models.bookshop.enums.EditionType;
import bookshopsystem.repositories.bookshop.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private AuthorService authorService;
    private CategoryService categoryService;
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(AuthorService authorService, CategoryService categoryService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedDatabase(Path path) throws IOException {
        Files.readAllLines(path).forEach(line -> {
            Author author = authorService.getRandomAuthor();
            String[] data = line.split(" ");
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            String title = Arrays.stream(data)
                    .skip(5)
                    .collect(Collectors.joining(" "));
            Set<Category> categories = categoryService.getRandomCategories();

            Book book = new Book(
                    title,
                    null,
                    editionType,
                    price,
                    copies,
                    releaseDate,
                    ageRestriction,
                    author,
                    categories);

            bookRepository.save(book);
        });
    }


    @Override
    public List<Book> getBooksReleasedAfter(int year) {
        return bookRepository.getBooksByReleaseDateAfter(LocalDate.of(year, 1, 1));
    }

    @Override
    public List<Book> getBooksFrom(String firstName, String lastName) {
        return bookRepository.getBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }

    @Override
    public List<Book> getBooksBy(AgeRestriction ageRestriction) {
        return bookRepository.getBooksByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> getBooksByEditionAndNumberOfCopiesLowerThan(EditionType editionType, int copies) {
        return bookRepository.getBooksByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    @Override
    public List<Book> getBooksWithPriceNotBetween(BigDecimal valueOne, BigDecimal valueTwo) {
        return bookRepository.getBooksByPriceIsLessThanOrPriceIsGreaterThan(valueOne, valueTwo);
    }

    @Override
    public List<Book> getBooksNotReleasedDuringYear(int year) {
        return bookRepository.getBooksByReleaseDateBeforeOrReleaseDateAfter(
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 12, 31)
        );
    }

    @Override
    public List<Book> getBooksReleasedBefore(LocalDate date) {
        return bookRepository.getBooksByReleaseDateBefore(date);
    }

    @Override
    public List<Book> getBooksTitleContaining(String value) {
        return bookRepository.getBooksByTitleContaining(value);
    }

    @Override
    public List<Book> getBooksByAuthorLastNameStartsWith(String value) {
        return bookRepository.getBooksByAuthorLastNameStartingWith(value);
    }

    @Override
    public int getCountOfBooksWithTitleLongerThan(int value) {
        return bookRepository.countBooksByTitleLengthGreaterThan(value);
    }

    @Override
    public String getBookInfo(String title) {
        String[] bookInfo = bookRepository.getBookInfo(title).split(",");
        return String.format("%s %s %s %.2f",
                bookInfo[0],
                bookInfo[1],
                bookInfo[2],
                Double.parseDouble(bookInfo[3]));
    }

    @Override
    public int increaseCopiesOfBooksReleasedAfter(LocalDate date, int copies) {
        return bookRepository.increaseCopiesOfBooksReleasedAfter(date, copies) * copies;
    }
}

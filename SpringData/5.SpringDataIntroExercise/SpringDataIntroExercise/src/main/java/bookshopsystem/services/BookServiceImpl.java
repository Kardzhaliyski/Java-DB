package bookshopsystem.services;

import bookshopsystem.models.Author;
import bookshopsystem.models.Book;
import bookshopsystem.models.Category;
import bookshopsystem.models.enums.AgeRestriction;
import bookshopsystem.models.enums.EditionType;
import bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

        Book[] getBooksReleasedAfter(LocalDate date);
    }
}

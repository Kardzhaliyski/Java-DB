package bookshopsystem;

import bookshopsystem.services.AuthorService;
import bookshopsystem.services.BookService;
import bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {
    public static final String RESOURCE_PATH = "resources/";
    public static final String BOOKS_FILE_NAME = "books.txt";
    public static final String AUTHORS_FILE_NAME = "authors.txt";
    public static final String CATEGORIES_FILE_NAME = "categories.txt";


    AuthorService authorService;
    CategoryService categoryService;
    BookService bookService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args)  {
//        System.out.println(authorService.getAuthorsWithBooksReleasedBefore(1988));
//        bookService.getBooksReleasedAfter(2000).forEach(b -> System.out.println(b.getTitle() + " - " + b.getReleaseDate()));
        authorService.getAllOrderedByBooksCount().forEach(a -> System.out.printf("%s %s %n", a.getFirstName(), a.getLastName()));
//        authorService.getAllOrderedByBooksCount().forEach(author -> {
//            System.out.println(author.getBooks());
//        });
    }
}

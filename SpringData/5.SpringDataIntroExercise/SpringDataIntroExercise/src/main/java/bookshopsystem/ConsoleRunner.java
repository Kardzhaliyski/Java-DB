package bookshopsystem;

import bookshopsystem.models.user.User;
import bookshopsystem.repositories.user.UserRepository;
import bookshopsystem.services.bookshop.AuthorService;
import bookshopsystem.services.bookshop.BookService;
import bookshopsystem.services.bookshop.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class ConsoleRunner implements CommandLineRunner {
    public static final String RESOURCE_PATH = "src/main/resources/files/";
    public static final String BOOKS_FILE_NAME = "books.txt";
    public static final String AUTHORS_FILE_NAME = "authors.txt";
    public static final String CATEGORIES_FILE_NAME = "categories.txt";


    AuthorService authorService;
    CategoryService categoryService;
    BookService bookService;
    UserRepository userRepository;

    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService, UserRepository userRepository) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws IOException {
        authorService.seedDatabase(Path.of(RESOURCE_PATH + AUTHORS_FILE_NAME));
    }
}

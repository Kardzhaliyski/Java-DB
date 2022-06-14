package bookshopsystem;

import bookshopsystem.models.user.User;
import bookshopsystem.repositories.user.UserRepository;
import bookshopsystem.services.AuthorService;
import bookshopsystem.services.BookService;
import bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    public static final String RESOURCE_PATH = "resources/";
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
    public void run(String... args) {
        User user = new User("Pesho", "@Zzzzs4t9o", "Pesho@gmail.com", 23);

    }
}

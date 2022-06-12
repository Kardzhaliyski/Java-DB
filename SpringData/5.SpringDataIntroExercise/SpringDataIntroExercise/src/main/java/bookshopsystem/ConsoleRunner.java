package bookshopsystem;

import bookshopsystem.services.AuthorService;
import bookshopsystem.services.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class ConsoleRunner implements CommandLineRunner {
    public static final String RESOURCE_PATH = "resources/";
    public static final String BOOKS_FILE_NAME = "books.txt";
    public static final String AUTHORS_FILE_NAME = "authors.txt";
    public static final String CATEGORIES_FILE_NAME = "categories.txt";


    AuthorService authorService;

    @Autowired
    public ConsoleRunner(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(authorService.getRandomAuthor());
//        authorService.seedDatabase(Path.of(RESOURCE_PATH + AUTHORS_FILE_NAME));
    }


//        System.out.println(p1.toAbsolutePath());
//        System.out.println(Paths.get("/mnt/plex/Archive/Git/Java-DB/SpringData/5.SpringDataIntroExercise/SpringDataIntroExercise/resources/books.txt").toAbsolutePath());
}

package bookshopsystem.services.bookshop;

import bookshopsystem.models.bookshop.Author;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();
    void seedDatabase(Path path) throws IOException;
    List<Author> getAuthorsWithBooksReleasedBefore(int year);
    List<Author> getAllOrderedByBooksCount();

}

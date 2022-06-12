package bookshopsystem.services;

import bookshopsystem.models.Author;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();
    void seedDatabase(Path path) throws IOException;
    List<Author> getAuthorsWithBooksReleasedBefore(int year);

}

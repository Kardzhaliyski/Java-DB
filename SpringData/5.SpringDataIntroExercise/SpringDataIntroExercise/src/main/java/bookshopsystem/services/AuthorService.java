package bookshopsystem.services;

import bookshopsystem.models.Author;

import java.io.IOException;
import java.nio.file.Path;

public interface AuthorService {
    Author getRandomAuthor();
    void seedDatabase(Path path) throws IOException;
}

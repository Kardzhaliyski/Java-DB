package bookshopsystem.services;

import bookshopsystem.models.Book;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

public interface BookService {
    void seedDatabase(Path path) throws IOException;
    Book[] getBooksReleasedAfter(LocalDate date);
}

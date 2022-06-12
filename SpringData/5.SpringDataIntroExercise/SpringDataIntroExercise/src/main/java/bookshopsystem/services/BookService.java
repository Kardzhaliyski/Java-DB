package bookshopsystem.services;

import bookshopsystem.models.Book;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface BookService {
    void seedDatabase(Path path) throws IOException;
    List<Book> getBooksReleasedAfter(int year);
}

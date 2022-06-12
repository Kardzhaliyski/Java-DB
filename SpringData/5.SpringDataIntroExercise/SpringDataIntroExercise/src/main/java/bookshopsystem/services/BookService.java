package bookshopsystem.services;

import java.io.IOException;
import java.nio.file.Path;

public interface BookService {
    void seedDatabase(Path path) throws IOException;
}

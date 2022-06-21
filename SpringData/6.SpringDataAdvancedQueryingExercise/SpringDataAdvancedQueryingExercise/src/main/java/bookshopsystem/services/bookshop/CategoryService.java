package bookshopsystem.services.bookshop;

import bookshopsystem.models.bookshop.Category;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public interface CategoryService {
    void seedDatabase(Path path) throws IOException;
    Set<Category> getRandomCategories();
}

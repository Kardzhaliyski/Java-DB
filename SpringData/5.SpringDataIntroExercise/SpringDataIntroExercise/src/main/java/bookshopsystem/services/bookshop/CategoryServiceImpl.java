package bookshopsystem.services.bookshop;

import bookshopsystem.models.bookshop.Category;
import bookshopsystem.repositories.bookshop.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private Set<Category> categories;
    private Random random;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        setCategories();
        random = new Random();
    }

    private void setCategories() {
        categories = new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public void seedDatabase(Path path) throws IOException {
        Files.readAllLines(path).forEach(line -> {
                if(line.isBlank()) {
                    return;
                }

                String categoryName = line.trim();

                Category category = new Category(categoryName);
                categoryRepository.save(category);
        });

        setCategories();
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> tempCategorySet = new HashSet<>();
        List<Category> categoryList = new ArrayList<>(categories);
        for (int i = 0; i <= random.nextInt(categories.size()); i++) {
            tempCategorySet.add(categoryList.get(random.nextInt(categories.size())));
        }

        return tempCategorySet;
    }


}

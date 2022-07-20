package com.example.demo.services;

import com.example.demo.entities.categories.Category;
import com.example.demo.entities.categories.CategorySummaryDTO;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private Set<Category> categories;
    private final Random random;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        setCategories();
        this.random = new Random();
    }

    private void setCategories() {
        this.categories = new HashSet<>(categoryRepository.findAll());
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

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<CategorySummaryDTO> getCategoriesSummarys() {
        return categoryRepository.findAllCategoriesData();
    }
}

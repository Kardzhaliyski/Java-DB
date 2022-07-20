package com.example.demo.services;

import com.example.demo.entities.categories.Category;
import com.example.demo.entities.categories.CategorySummaryDTO;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();

    void save(Category category);
    List<CategorySummaryDTO> getCategoriesSummarys();
}

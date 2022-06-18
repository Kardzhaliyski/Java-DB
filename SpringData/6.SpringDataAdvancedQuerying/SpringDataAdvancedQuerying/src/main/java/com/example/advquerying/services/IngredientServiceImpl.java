package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getByNameStartingWith(String value) {
        return ingredientRepository.findByNameStartingWith(value);
    }

    @Override
    public List<Ingredient> getByNames(List<String> ingredientsNames) {
        return ingredientRepository.findByNameInOrderByPriceAsc(ingredientsNames);
    }
}

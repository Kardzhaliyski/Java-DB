package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final ShampooRepository shampooRepository;
    private final ShampooService shampooService;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, ShampooRepository shampooRepository, ShampooService shampooService) {
        this.ingredientRepository = ingredientRepository;
        this.shampooRepository = shampooRepository;
        this.shampooService = shampooService;
    }

    @Override
    public List<Ingredient> getByNameStartingWith(String value) {
        return ingredientRepository.findByNameStartingWith(value);
    }

    @Override
    public List<Ingredient> getByNames(List<String> ingredientsNames) {
        return ingredientRepository.findByNameInOrderByPriceAsc(ingredientsNames);
    }

    @Override
    public void deleteByName(String name) {
        shampooService.deleteByIngredientName(name);
        ingredientRepository.deleteIngredientByName(name);
//        Ingredient ingredient = ingredientRepository.findByName(name);
//        delete(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        Shampoo[] shampoos = ingredient.getShampoos().toArray(Shampoo[]::new);
        for (Shampoo shampoo : shampoos) {
            shampooService.delete(shampoo);
        }
        ingredientRepository.delete(ingredient);
    }
}

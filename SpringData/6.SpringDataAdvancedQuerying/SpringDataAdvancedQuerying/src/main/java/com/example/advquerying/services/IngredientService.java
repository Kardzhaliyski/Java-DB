package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getByNameStartingWith(String value);

    List<Ingredient> getByNames(List<String> ingredientsNames);
    void deleteByName(String name);

    void delete(Ingredient ingredient);

    void increaseAllPricesBy(Double percentage);
}

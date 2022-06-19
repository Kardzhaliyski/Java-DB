package com.example.advquerying.services;


import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> getBySize(Size size);

    List<Shampoo> getBySizeOrLabelId(Size size, Long id);

    List<Shampoo> getByPriceGreaterThan(BigDecimal price);
    int getCountByPriceLessThan(BigDecimal price);
    List<Shampoo> getAllContainingIngredients(List<String> ingredients);
    List<Shampoo> getByIngredientsCountGreaterThan(int count);
    List<Shampoo> getByIngredientsCountLesserThan(int count);

    void delete(Shampoo shampoo);

    void deleteByIngredientName(String name);
}

package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> getBySize(Size size) {
        return shampooRepository.findBySizeOrderByIdAsc(size);
    }

    @Override
    public List<Shampoo> getBySizeOrLabelId(Size size, Long id) {
        return shampooRepository.findBySizeOrLabelIdOrderByPriceAsc(size, id);
    }

    @Override
    public List<Shampoo> getByPriceGreaterThan(BigDecimal price) {
        return shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int getCountByPriceLessThan(BigDecimal price) {
        return shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> getAllContainingIngredients(List<String> ingredients) {
//        return shampooRepository.findByIngredientsNameIn(ingredients);
        return shampooRepository.findByIngredientsNames(ingredients);
    }

    @Override
    public List<Shampoo> getByIngredientsCountGreaterThan(int count) {
        return shampooRepository.findByIngredientsCountGreaterThan(count);
    }

    @Override
    public List<Shampoo> getByIngredientsCountLesserThan(int count) {
        return shampooRepository.findByIngredientsCountLesserThan(count);
    }

    @Override
    public void delete(Shampoo shampoo) {
        Ingredient[] ingredients = shampoo.getIngredients().toArray(Ingredient[]::new);
        for (Ingredient ingredient : ingredients) {
            ingredient.remove(shampoo);
        }
        shampooRepository.delete(shampoo);
    }

}

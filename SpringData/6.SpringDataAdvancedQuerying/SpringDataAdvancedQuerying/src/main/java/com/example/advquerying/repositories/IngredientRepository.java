package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String string);

    Ingredient findByName(String name);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> ingredientsNames);

    //    int deleteByName(String name);
    @Modifying
    @Query("DELETE FROM Ingredient i WHERE i.name = :name")
    void deleteIngredientByName(String name);

    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * ((:percentage / 100.0) + 1)")
    void increaseAllPricesBy(Double percentage);
}

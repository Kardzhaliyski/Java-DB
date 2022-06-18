package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartingWith(String string);
    Ingredient findByName(String name);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> ingredientsNames);

    int deleteByName(String name);
//    @Modifying
//    @Query("DELETE FROM Ingredient i WHERE i.name = :name")
//    void deleteIngredientByName(String name);
}

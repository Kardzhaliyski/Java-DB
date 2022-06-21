package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findBySizeOrderByIdAsc(Size size);
    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long id);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    int countByPriceLessThan(BigDecimal price);
//    List<Shampoo> findByIngredientsNameIn(List<String> names);
    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :names")
    List<Shampoo> findByIngredientsNames(@Param("names") List<String> names);
    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size > :count")
    List<Shampoo> findByIngredientsCountGreaterThan(@Param("count") int count);
    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :count")
    List<Shampoo> findByIngredientsCountLesserThan(int count);
    int deleteByIngredientsName(String ingredientName);
}

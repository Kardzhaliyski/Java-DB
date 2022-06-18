package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findBySizeOrderByIdAsc(Size size);
    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, Long id);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    int countByPriceLessThan(BigDecimal price);

}

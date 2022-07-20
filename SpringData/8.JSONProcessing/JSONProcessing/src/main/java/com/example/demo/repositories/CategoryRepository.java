package com.example.demo.repositories;

import com.example.demo.entities.categories.CategorySummaryDTO;
import com.example.demo.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT" +
            " new com.example.demo.entities.categories.CategorySummaryDTO(" +
            " c.name as name," +
            " count(p) AS count," +
            " avg(p.price) as averagePrice," +
            " sum(p.price) as totalRevenue)" +
            " FROM Category c" +
            " JOIN c.products p" +
            " GROUP BY c" +
            " ORDER BY count(p) ASC")
    List<CategorySummaryDTO> findAllCategoriesData();
}

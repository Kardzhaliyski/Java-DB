package com.example.demo.repositories;

import com.example.demo.entities.products.SoldProductsDTO;
import com.example.demo.entities.products.Product;
import com.example.demo.entities.products.ProductWithoutBuyerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductWithoutBuyerDTO> findAllProductsByBuyerIsNullAndPriceBetweenOrderByPriceAsc(BigDecimal from, BigDecimal to);
    List<SoldProductsDTO> findAllProductsByBuyerIsNotNull();

}

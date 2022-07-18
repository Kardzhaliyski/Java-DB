package com.example.demo.services;

import com.example.demo.entities.products.Product;
import com.example.demo.entities.products.ProductWithoutBuyerDTO;

import java.util.List;

public interface ProductService {
    void save(Product product);
    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSale(Double from, Double to);

}

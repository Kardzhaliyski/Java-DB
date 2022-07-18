package com.example.demo.services;

import com.example.demo.entities.products.Product;
import com.example.demo.entities.products.ProductWithoutBuyerDTO;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSale(Double from, Double to) {
        return productRepository
                .findAllProductsByBuyerIsNullAndPriceBetweenOrderByPriceAsc(
                        BigDecimal.valueOf(from),
                        BigDecimal.valueOf(to));

    }
}

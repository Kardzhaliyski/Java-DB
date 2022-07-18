package com.example.demo;

import com.example.demo.entities.products.SoldProductsDTO;
import com.example.demo.entities.products.ProductWithoutBuyerDTO;
import com.example.demo.entities.users.UserWithSellsDTO;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private ProductService productService;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private UserService userService;
    private Gson gson;

    @Autowired
    public Runner(ProductService productService, ProductRepository productRepository, UserRepository userRepository, UserService userService) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.gson = new Gson()
                .newBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(toJSONProductsForSaleInRange(500.0, 1000.0));
//        System.out.println(toJSONAllBoughtProducts());
//        List<UserWithSellsDTO> sellers = userRepository.findAllBySoldProductsBuyerIsNotNull();
        System.out.println(toJSONAllUsersWithSells());
        System.out.println();
    }

    private String toJSONAllUsersWithSells() {
        List<UserWithSellsDTO> allUsersWithSells = userService.getAllUsersWithSells();
        return gson.toJson(allUsersWithSells);
    }

    private String toJSONAllBoughtProducts() {
        List<SoldProductsDTO> products = productRepository.findAllProductsByBuyerIsNotNull();
        String json = gson.toJson(products);
        return json;
    }

    private String toJSONProductsForSaleInRange(Double from, Double to) {
        List<ProductWithoutBuyerDTO> products = productService.getProductsInPriceRangeForSale(from, to);
        String json = gson.toJson(products);
        return json;
    }
}

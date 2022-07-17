package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.entities.products.Product;
import com.example.demo.entities.products.ProductImportDTO;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final static String USER_SEED_PATH = "src/main/resources/users.json";
    private final static String CATEGORY_SEED_PATH = "src/main/resources/categories.json";
    private static final String PRODUCT_SEED_PATH = "src/main/resources/products.json";
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        gson = new Gson().newBuilder().setPrettyPrinting().create();
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
    }

    @Override
    public void seedUsers() {
        UserImportDTO[] userImportDTOS;
        try (Reader seedFile = new FileReader(USER_SEED_PATH)) {
            userImportDTOS = gson.fromJson(seedFile, UserImportDTO[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<User> users = Arrays.stream(userImportDTOS)
                .map(importDTO -> modelMapper.map(importDTO, User.class))
                .collect(Collectors.toList());
        users.forEach(userService::save);
    }

    @Override
    public void seedCatorogies() {
        CategoryImportDTO[] categoryImportDTOS;
        try (Reader seedFile = new FileReader(CATEGORY_SEED_PATH)) {
            categoryImportDTOS = gson.fromJson(seedFile, CategoryImportDTO[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Category> categories = Arrays.stream(categoryImportDTOS)
                .map(importDTO -> modelMapper.map(importDTO, Category.class))
                .collect(Collectors.toList());

        categories.forEach(categoryService::save);
    }

    @Override
    public void seedProducts() {
        ProductImportDTO[] productImportDTOS;
        try (Reader seedFile = new FileReader(PRODUCT_SEED_PATH)) {
            productImportDTOS = gson.fromJson(seedFile, ProductImportDTO[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Product> products = Arrays.stream(productImportDTOS)
                .map(importDTO -> modelMapper.map(importDTO, Product.class))
                .collect(Collectors.toList());
        products.forEach(p -> p.setSeller(userService.getRandomUser()));
        products.forEach(p -> p.addCategories(categoryService.getRandomCategories()));
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            product.setSeller(userService.getRandomUser());
            product.addCategories(categoryService.getRandomCategories());
            if(i % 2 == 0) {
                User buyer = userService.getRandomUser();
                if(buyer != product.getSeller()) {
                    product.setBuyer(buyer);
                }
            }

            productService.save(product);
        }
    }


}

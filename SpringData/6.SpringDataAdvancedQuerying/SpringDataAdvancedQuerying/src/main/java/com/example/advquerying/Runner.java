package com.example.advquerying;

import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class Runner implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Runner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
//        ingredientService.deleteByName("Apple");
//        ingredientService.deleteByName("Nettle");
//        ingredientService.deleteByName("Macadamia Oil");
//        ingredientService.deleteByName("Aloe Vera");
//        ingredientService.deleteByName("Lavender");
//        ingredientService.deleteByName("Herbs");
//        ingredientService.deleteByName("Wild Rose");
//        ingredientService.deleteByName("Micro-Crystals");

        double percentage = 10.0;
        ingredientService.increaseAllPricesBy(percentage);

    }
}

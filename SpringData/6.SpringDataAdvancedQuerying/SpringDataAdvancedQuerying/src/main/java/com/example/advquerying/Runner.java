package com.example.advquerying;

import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Runner implements CommandLineRunner {
    private final ShampooService shampooService;

    @Autowired
    public Runner(ShampooService shampooService) {
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {
        shampooService.getByPriceGreaterThan(BigDecimal.valueOf(6))
                .forEach(System.out::println);
    }
}

package com.example.springdataautomappingobjectsexercise;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    private final MenuService menuService;


    @Autowired
    public Runner(MenuService menuService) {
        this.menuService = menuService;
    }


    @Override
    public void run(String... args) throws Exception {
        menuService.start();
    }

}

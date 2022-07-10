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
    private final GameService gameService;
    private final Validator validator;


    @Autowired
    public Runner(MenuService menuService, GameService gameService, Validator validator) {
        this.menuService = menuService;
        this.gameService = gameService;
        this.validator = validator;
    }


    @Override
    public void run(String... args) throws Exception {
        menuService.start();
//        gameService.printAllGame();

//        Set<ConstraintViolation<Game>> constraintViolations = validator.validateValue(Game.class, "size", -5.0);
//        for (ConstraintViolation<Game> violation : constraintViolations) {
//            System.out.println(violation);
//        }
    }

}

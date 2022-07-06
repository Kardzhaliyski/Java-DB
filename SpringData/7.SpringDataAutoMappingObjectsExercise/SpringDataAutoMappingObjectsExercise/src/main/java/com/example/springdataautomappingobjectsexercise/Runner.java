package com.example.springdataautomappingobjectsexercise;

import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final MenuService menuService;
    private final GameService gameService;


    @Autowired
    public Runner(MenuService menuService, GameService gameService) {
        this.menuService = menuService;
        this.gameService = gameService;
    }


    @Override
    public void run(String... args) throws Exception {
        menuService.start();
//        gameService.printAllGame();

    }

}

package com.example.springdataautomappingobjectsexercise;

import com.example.springdataautomappingobjectsexercise.models.menus.MainMenu;
import com.example.springdataautomappingobjectsexercise.models.menus.Menu;
import com.example.springdataautomappingobjectsexercise.models.menus.StartMenu;
import com.example.springdataautomappingobjectsexercise.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

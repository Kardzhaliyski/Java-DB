package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.menus.Menu;
import com.example.springdataautomappingobjectsexercise.models.menus.StartMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    Menu menu;

    @Autowired
    public MenuServiceImpl(StartMenu startMenu) {
        setMenu(startMenu);
    }

    private void setMenu(Menu menu) {
//        if (menu == null) {
//            throw new IllegalArgumentException("Menu should not be null!");
//        }

        this.menu = menu;
    }

    @Override
    public void start() {

        while (true) {
            Menu newMenu = this.menu.execute();
            setMenu(newMenu);
            if (menu == null) {
                break;
            }
        }
    }
}

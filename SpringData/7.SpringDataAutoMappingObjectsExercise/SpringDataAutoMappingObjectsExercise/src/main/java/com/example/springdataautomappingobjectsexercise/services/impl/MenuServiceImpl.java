package com.example.springdataautomappingobjectsexercise.services.impl;

import com.example.springdataautomappingobjectsexercise.models.menus.*;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    Menu menu;
    private final Menu startMenu;
    private final Menu adminMenu;
    private final Menu mainMenu;
    private final Menu editGameMenu;
    private final Menu deleteGameMenu;
    private final Menu shopMenu;

    @Autowired
    public MenuServiceImpl(StartMenu startMenu, AdminMenu adminMenu, MainMenu mainMenu, EditGameMenu editGameMenu, DeleteGameMenu deleteGameMenu, ShopMenu shopMenu) {
        this.startMenu = startMenu;
        this.adminMenu = adminMenu;
        this.mainMenu = mainMenu;
        this.editGameMenu = editGameMenu;
        this.deleteGameMenu = deleteGameMenu;
        this.shopMenu = shopMenu;
        setMenu(startMenu);
    }

    private void setMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("Menu should not be null!");
        }

        this.menu = menu;
    }

    @Override
    public void start() {

        while (true) {
            Menu newMenu = parseMenu(this.menu.execute());
            setMenu(newMenu);
            if (menu == null) {
                break;
            }
        }
    }

    private Menu parseMenu(MenuType type) {
        System.out.println();
        switch (type){
            case START_MENU:
                return startMenu;
            case ADMIN_MENU:
                return adminMenu;
            case MAIN_MENU:
                return mainMenu;
            case EDIT_GAME_MENU:
                return editGameMenu;
            case DELETE_GAME_MENU:
                return deleteGameMenu;
            case SHOP_MENU:
                return shopMenu;
        }
        return null;
    }


}

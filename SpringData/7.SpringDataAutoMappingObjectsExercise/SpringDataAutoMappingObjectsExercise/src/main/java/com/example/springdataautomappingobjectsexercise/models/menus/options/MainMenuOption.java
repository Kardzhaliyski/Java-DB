package com.example.springdataautomappingobjectsexercise.models.menus.options;

import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;

public enum MainMenuOption implements MenuOption {
    SHOW_ALL_GAMES,
    SHOW_OWNED_GAMES,
    LOGOUT;


    @Override
    public String menuName() {
        return "Main Menu";
    }
}

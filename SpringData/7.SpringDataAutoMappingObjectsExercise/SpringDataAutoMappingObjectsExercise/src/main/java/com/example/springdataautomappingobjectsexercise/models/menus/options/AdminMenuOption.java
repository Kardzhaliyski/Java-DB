package com.example.springdataautomappingobjectsexercise.models.menus.options;

import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;

public enum AdminMenuOption implements MenuOption {
    ADD_GAME,
    EDIT_GAME,
    DELETE_GAME,
    LOGOUT;

    @Override
    public String menuName() {
        return "Admin Menu";
    }

    @Override
    public String nameCapitalized() {
        return MenuOption.super.nameCapitalized();
    }
}

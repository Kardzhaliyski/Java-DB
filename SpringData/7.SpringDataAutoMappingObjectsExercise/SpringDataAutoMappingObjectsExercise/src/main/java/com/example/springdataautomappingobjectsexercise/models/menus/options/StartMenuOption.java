package com.example.springdataautomappingobjectsexercise.models.menus.options;

import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;

public enum StartMenuOption implements MenuOption {
    LOGIN,
    REGISTER;


    @Override
    public String menuName() {
        return "Start Menu";
    }
}

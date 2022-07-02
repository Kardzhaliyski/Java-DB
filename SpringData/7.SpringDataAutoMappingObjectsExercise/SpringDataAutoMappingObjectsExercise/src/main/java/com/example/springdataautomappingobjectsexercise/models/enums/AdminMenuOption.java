package com.example.springdataautomappingobjectsexercise.models.enums;

public enum AdminMenuOption implements MenuOption{
    ADD_GAME,
    EDIT_GAME,
    DELETE_GAME;

    @Override
    public String menuName() {
        return "Admin Menu";
    }

    @Override
    public String nameCapitalized() {
        return MenuOption.super.nameCapitalized();
    }
}

package com.example.springdataautomappingobjectsexercise.models.menus.options;

public enum DeleteGameMenuOption implements MenuOption{
    CHOSE_GAME,
    CANCEL;

    @Override
    public String menuName() {
        return "Delete Game Menu";
    }

    @Override
    public String nameCapitalized() {
        return MenuOption.super.nameCapitalized();
    }
}

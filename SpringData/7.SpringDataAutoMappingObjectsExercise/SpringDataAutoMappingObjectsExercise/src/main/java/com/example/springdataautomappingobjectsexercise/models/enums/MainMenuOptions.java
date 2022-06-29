package com.example.springdataautomappingobjectsexercise.models.enums;

public enum MainMenuOptions implements MenuOptions {
    ALL_GAMES,
    OWNED_GAMES;

    @Override
    public String nameCapitalized() {
        String name = this.name();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }

    @Override
    public String menuName() {
        return "Main Menu";
    }
}

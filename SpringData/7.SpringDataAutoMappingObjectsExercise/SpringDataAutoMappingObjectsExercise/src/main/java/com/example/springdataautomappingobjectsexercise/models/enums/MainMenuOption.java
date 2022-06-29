package com.example.springdataautomappingobjectsexercise.models.enums;

public enum MainMenuOption implements MenuOption {
    SHOW_ALL_GAMES,
    SHOW_OWNED_GAMES;


    @Override
    public String menuName() {
        return "Main Menu";
    }
}

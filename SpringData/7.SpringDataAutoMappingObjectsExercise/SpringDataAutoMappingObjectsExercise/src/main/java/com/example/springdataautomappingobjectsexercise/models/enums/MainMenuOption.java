package com.example.springdataautomappingobjectsexercise.models.enums;

public enum MainMenuOption implements MenuOption {
    ALL_GAMES,
    OWNED_GAMES;


    @Override
    public String menuName() {
        return "Main Menu";
    }
}

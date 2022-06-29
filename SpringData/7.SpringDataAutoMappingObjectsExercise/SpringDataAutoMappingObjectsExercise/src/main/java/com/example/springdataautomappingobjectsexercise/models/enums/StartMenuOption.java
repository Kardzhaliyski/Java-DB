package com.example.springdataautomappingobjectsexercise.models.enums;

public enum StartMenuOption implements MenuOption {
    LOGIN,
    REGISTER;


    @Override
    public String menuName() {
        return "Start Menu";
    }
}

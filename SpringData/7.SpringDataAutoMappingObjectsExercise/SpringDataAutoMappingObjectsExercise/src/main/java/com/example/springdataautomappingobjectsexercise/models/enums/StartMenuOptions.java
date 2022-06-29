package com.example.springdataautomappingobjectsexercise.models.enums;

public enum StartMenuOptions implements MenuOptions {
    LOGIN,
    REGISTER;

    @Override
    public String nameCapitalized() {
        String name = this.name();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }

    @Override
    public String menuName() {
        return "Start Menu";
    }
}

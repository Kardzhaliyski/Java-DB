package com.example.springdataautomappingobjectsexercise.models.menus.options;

public interface MenuOption {
    String name();
    int ordinal();
    String menuName();

    default String nameCapitalized() {
        String name = this.name().replace('_' , ' ');
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }

}

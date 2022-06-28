package com.example.springdataautomappingobjectsexercise.models.enums;

public class BaseMenu<T extends Enum<T> & Menu> {
    private final T menu;

    public BaseMenu(T menu) {
        this.menu = menu;
    }
}

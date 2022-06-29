package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.enums.MainMenuOption;

public class MainMenu extends MenuImpl{

    @Override
    public void display() {
        super.display(MainMenuOption.values());
    }
}

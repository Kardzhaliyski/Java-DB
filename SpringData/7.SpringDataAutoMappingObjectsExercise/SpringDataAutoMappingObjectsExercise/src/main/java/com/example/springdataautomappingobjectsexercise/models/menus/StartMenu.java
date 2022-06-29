package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.enums.StartMenuOption;

public class StartMenu extends MenuImpl{
    @Override
    public void display() {
        super.display(StartMenuOption.values());
    }
}

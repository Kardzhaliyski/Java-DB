package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.enums.MainMenuOption;
import com.example.springdataautomappingobjectsexercise.models.enums.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import org.springframework.stereotype.Component;

@Component
public class MainMenu extends MenuImpl {
    private static final MenuOption[] MENU_OPTIONS = MainMenuOption.values();

    protected MainMenu(ReaderService reader) {
        super(reader, MENU_OPTIONS);
    }

    @Override
    protected Menu executeMenuOption(MenuOption menu) {
        return null;
        //todo
    }
}


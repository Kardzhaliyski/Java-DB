package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.enums.AdminMenuOption;
import com.example.springdataautomappingobjectsexercise.models.enums.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMenu extends MenuImpl {
    private final static MenuOption[] MENU_OPTIONS = AdminMenuOption.values();


    @Autowired
    protected AdminMenu(ReaderService reader) {
        super(reader, MENU_OPTIONS);
    }

    @Override
    protected Menu executeMenuOption(MenuOption menu) {
        return null;
    }
}

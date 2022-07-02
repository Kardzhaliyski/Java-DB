package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MainMenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenu extends MenuImpl {
    private static final MenuOption[] MENU_OPTIONS = MainMenuOption.values();
    private UserService userService;

    @Autowired
    protected MainMenu(ReaderService reader, UserService userService) {
        super(reader, MENU_OPTIONS);
        this.userService = userService;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((MainMenuOption) menu) {

            case SHOW_ALL_GAMES:
                break;
            case SHOW_OWNED_GAMES:
                break;
            case LOGOUT:
                userService.logout();
                return MenuType.START_MENU;
        }
        return null;
    }
}


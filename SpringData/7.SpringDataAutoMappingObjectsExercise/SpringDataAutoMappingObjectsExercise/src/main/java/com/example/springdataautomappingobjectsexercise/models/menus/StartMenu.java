package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.entities.User;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.StartMenuOption;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartMenu extends MenuImpl {
    private final static MenuOption[] MENU_OPTIONS = StartMenuOption.values();
    private final UserService userService;
    @Autowired
    protected StartMenu(ReaderService reader, UserService userService) {
        super(reader, MENU_OPTIONS);
        this.userService = userService;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((StartMenuOption) menu) {
            case LOGIN: {
                User user = userService.login();
                if(user.isAdmin()) {
                    return MenuType.ADMIN_MENU;
                } else {
                    return MenuType.MAIN_MENU;
                }
            }
            case REGISTER: {
                userService.register();
                return MenuType.START_MENU;
            }
        }

        return MenuType.START_MENU;
    }
}

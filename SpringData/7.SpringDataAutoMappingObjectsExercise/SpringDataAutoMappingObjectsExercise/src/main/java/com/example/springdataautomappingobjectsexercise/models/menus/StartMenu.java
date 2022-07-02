package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.entities.User;
import com.example.springdataautomappingobjectsexercise.models.enums.MenuOption;
import com.example.springdataautomappingobjectsexercise.models.enums.StartMenuOption;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartMenu extends MenuImpl {
    private final static MenuOption[] MENU_OPTIONS = StartMenuOption.values();
    private final UserService userService;
    private final MainMenu mainMenu;
    private final AdminMenu adminMenu;
    @Autowired
    protected StartMenu(ReaderService reader, UserService userService, MainMenu mainMenu, AdminMenu adminMenu) {
        super(reader, MENU_OPTIONS);
        this.userService = userService;
        this.mainMenu = mainMenu;
        this.adminMenu = adminMenu;
    }

    @Override
    protected Menu executeMenuOption(MenuOption menu) {
        switch ((StartMenuOption) menu) {
            case LOGIN: {
                User user = userService.login();
                if(user.isAdmin()) {
                    return adminMenu;
                } else {
                    return mainMenu;
                }
            }
            case REGISTER: {
                userService.register();
                return this;
            }
        }

        return null;
    }
}

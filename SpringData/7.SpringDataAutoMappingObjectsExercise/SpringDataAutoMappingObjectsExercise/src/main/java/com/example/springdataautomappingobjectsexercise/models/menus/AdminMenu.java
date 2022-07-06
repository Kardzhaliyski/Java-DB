package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.AdminMenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMenu extends MenuImpl {
    private final static MenuOption[] MENU_OPTIONS = AdminMenuOption.values();
    private final GameService gameService;
    private final UserService userService;

    @Autowired
    protected AdminMenu(ReaderService reader, GameService gameService, UserService userService) {
        super(reader, MENU_OPTIONS);
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((AdminMenuOption) menu) {
            case ADD_GAME: {
                gameService.addGame();
                return MenuType.ADMIN_MENU;
            }
            case EDIT_GAME:
                return MenuType.EDIT_GAME_MENU;
            case DELETE_GAME:
                break;
            case LOGOUT:
                userService.logout();
                return MenuType.START_MENU;
        }

        return null;
    }

}

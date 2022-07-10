package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MainMenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenu extends MenuImpl {
    private static final MenuOption[] MENU_OPTIONS = MainMenuOption.values();
    private UserService userService;
    private final GameService gameService;

    @Autowired
    protected MainMenu(ReaderService reader, UserService userService, GameService gameService) {
        super(reader, MENU_OPTIONS);
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((MainMenuOption) menu) {

            case SHOP:
                break;
            case SHOW_ALL_GAMES:
                gameService.printAllGame();
                break;
            case SHOW_GAME_INFO:
                gameService.printAllGame();
                Game game = gameService.selectGame();
                gameService.printGameDetails(game);
                break;
            case SHOW_OWNED_GAMES:
                break;
            case LOGOUT:
                userService.logout();
                return MenuType.START_MENU;
        }
        return MenuType.MAIN_MENU;
    }
}


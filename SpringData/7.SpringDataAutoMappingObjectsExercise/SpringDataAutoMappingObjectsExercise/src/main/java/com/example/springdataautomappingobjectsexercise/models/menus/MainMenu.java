package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MainMenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.OrderService;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenu extends MenuImpl {
    private static final MenuOption[] MENU_OPTIONS = MainMenuOption.values();
    private UserService userService;
    private final GameService gameService;
    private final OrderService orderService;

    @Autowired
    protected MainMenu(ReaderService reader, UserService userService, GameService gameService, OrderService orderService) {
        super(reader, MENU_OPTIONS);
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((MainMenuOption) menu) {

            case SHOP:
                orderService.startOrder(userService.getUser());
                return MenuType.SHOP_MENU;
            case SHOW_ALL_GAMES:
                gameService.printAllGame();
                break;
            case SHOW_GAME_INFO:
                gameService.printAllGame();
                Game game = gameService.selectGame();
                gameService.printGameDetails(game);
                break;
            case SHOW_OWNED_GAMES:
                userService.printOwnedGames();
                break;
            case LOGOUT:
                userService.logout();
                return MenuType.START_MENU;
        }
        return MenuType.MAIN_MENU;
    }
}


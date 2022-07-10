package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.ShopMenuOption;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.OrderService;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.stereotype.Component;

import java.util.IllegalFormatException;

@Component
public class ShopMenu extends MenuImpl{
    private final static MenuOption[] MENU_OPTIONS = ShopMenuOption.values();
    private final GameService gameService;
    private final UserService userService;
    private final OrderService orderService;
    protected ShopMenu(ReaderService reader, GameService gameService, UserService userService, OrderService orderService) {
        super(reader, MENU_OPTIONS);
        this.gameService = gameService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((ShopMenuOption) menu) {
            case SHOW_CART:
                orderService.printCart();
                break;
            case ADD_GAME_TO_CART:
                gameService.printAllGame();
                Game game = gameService.selectGame();
                orderService.addGame(game);
                break;
            case REMOVE_GAME_FROM_CART:
                orderService.printCart();
                Long id = null;
                while (id == null) {
                    try {
                        System.out.print("Select id: ");
                        id = Long.parseLong(reader.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format for Id!");
                        continue;
                    }

                    if(id <= 0L) {
                        id = null;
                        System.out.println("Id should be positive number");
                    }
                }
                orderService.removeGame(id);
                break;
            case ORDER:
                orderService.finishOrder();
                return MenuType.MAIN_MENU;
        }
        return MenuType.SHOP_MENU;
    }
}

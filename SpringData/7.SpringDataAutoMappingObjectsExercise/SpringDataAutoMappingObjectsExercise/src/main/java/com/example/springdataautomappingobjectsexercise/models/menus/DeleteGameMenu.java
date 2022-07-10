package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuDelimiter;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.DeleteGameMenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteGameMenu extends MenuImpl {
    private final static MenuOption[] MENU_OPTIONS = DeleteGameMenuOption.values();
    private final GameService gameService;
    private Game game;

    @Autowired
    protected DeleteGameMenu(ReaderService reader, GameService gameService) {
        super(reader, MENU_OPTIONS);
        this.gameService = gameService;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((DeleteGameMenuOption) menu) {
            case CHOSE_GAME:
                gameService.printAllGame();
                Game game = gameService.selectGame();
                System.out.println(MenuDelimiter.LINE);
                System.out.println("Selected game: " + game.toString());
                System.out.println(MenuDelimiter.LINE);
                System.out.print("Confirm delete (y/n): ");
                String choice = reader.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.println("Game successful delete: " + gameService.deleteById(game.getId()));
                } else {
                    System.out.println("No game deleted!");
                }
                break;
            case CANCEL:
                break;
        }

        game = null;
        return MenuType.ADMIN_MENU;
    }
}

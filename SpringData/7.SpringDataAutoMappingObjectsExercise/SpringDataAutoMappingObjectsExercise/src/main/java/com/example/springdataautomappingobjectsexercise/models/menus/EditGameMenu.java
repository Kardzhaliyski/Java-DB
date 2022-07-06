package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuDelimiter;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.EditGameMenuOption;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.PublisherService;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditGameMenu extends MenuImpl {
    private final GameService gameService;
    private final PublisherService publisherService;
    private Game game;
    private Game gameCopy;
    private StringBuilder stringBuilder;
    @Autowired
    private final static MenuOption[] MENU_OPTIONS = EditGameMenuOption.values();

    protected EditGameMenu(ReaderService reader, GameService gameService, PublisherService publisherService) {
        super(reader, MENU_OPTIONS);
        this.gameService = gameService;
        this.publisherService = publisherService;
        this.stringBuilder = new StringBuilder();
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((EditGameMenuOption) menu) {
            case EDIT_TITLE:
                this.gameCopy.setTitle(gameService.readTitle());
                stringBuilder
                        .append("Title changed from '")
                        .append(game.getTitle())
                        .append("' To")
                        .append(gameCopy.getTitle())
                        .append(System.lineSeparator());

                break;
            case EDIT_PUBLISHER:
                String name = publisherService.readName();

                Publisher publisher = publisherService.getByName(name);
                if (publisher == null) {
                    publisher = publisherService.newPublisher(name);
                }

                this.gameCopy.setPublisher(publisher);
                stringBuilder.append("Publisher changed from ")
                        .append(game.getPublisher().getName())
                        .append(" to ")
                        .append(gameCopy.getPublisher().getName())
                        .append(System.lineSeparator());
                break;
            case EDIT_TRAILER_URL_ID:
                break;
            case EDIT_THUMBNAIL_URL:
                break;
            case EDIT_SIZE:
                break;
            case EDIT_PRICE:
                break;
            case EDIT_DESCRIPTION:
                break;
            case EDIT_RELEASE_DATE:
                break;
            case EDIT_PURCHASABLE:
                break;
            case SAVE:
                game.copy(gameCopy);
                gameService.save(this.game);
                this.game = null;
                this.gameCopy = null;
                System.out.println(stringBuilder);
                stringBuilder.setLength(0);
                return MenuType.ADMIN_MENU;
            case CANCEL:
                this.game = null;
                this.gameCopy = null;
                stringBuilder.setLength(0);
                return MenuType.ADMIN_MENU;
        }
        return MenuType.EDIT_GAME_MENU;
    }

    @Override
    public MenuType execute() {
        if (this.game == null) {
            gameService.printAllGame();
            selectGame();
        }
        return super.execute();
    }

    private void selectGame() {
        while (this.game == null) {
            System.out.println(MenuDelimiter.LONG_LINE);
            System.out.print("Chose game id to edit: ");
            Long id;
            try {
                id = Long.parseLong(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid id format!");
                continue;
            }

            this.game = gameService.getGame(id);
            this.gameCopy = new Game(this.game);
        }
    }
}

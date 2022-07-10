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

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class EditGameMenu extends MenuImpl {
    private final GameService gameService;
    private final PublisherService publisherService;
    private Game game;
    private Game.Builder gameCopy;
    private final Validator validator;
    @Autowired
    private final static MenuOption[] MENU_OPTIONS = EditGameMenuOption.values();

    protected EditGameMenu(ReaderService reader, GameService gameService, PublisherService publisherService, Validator validator) {
        super(reader, MENU_OPTIONS);
        this.gameService = gameService;
        this.publisherService = publisherService;
        this.validator = validator;
    }

    @Override
    protected MenuType executeMenuOption(MenuOption menu) {
        switch ((EditGameMenuOption) menu) {
            case EDIT_TITLE: {
                this.gameCopy.setTitle(gameService.readTitle());
                break;
            }
            case EDIT_PUBLISHER: {
                String name = publisherService.readName();
                Publisher publisher = publisherService.getByName(name);
                if (publisher == null) {
                    publisher = publisherService.newPublisher(name);
                }

                this.gameCopy.setPublisher(publisher);
                break;
            }
            case EDIT_TRAILER_URL_ID: {
                this.gameCopy.setTrailerUrlId(gameService.readTrailerUrlId());
                break;
            }
            case EDIT_THUMBNAIL_URL:
                this.gameCopy.setThumbnailUrl(gameService.readThumbnailUrl());
                break;
            case EDIT_SIZE:
                this.gameCopy.setSize(gameService.readGameSize());
                break;
            case EDIT_PRICE:
                this.gameCopy.setPrice(gameService.readPrice());
                break;
            case EDIT_DESCRIPTION:
                this.gameCopy.setDescription(gameService.readDescription());
                break;
            case EDIT_RELEASE_DATE:
                this.gameCopy.setReleaseDate(gameService.readReleaseDate());
                break;
            case EDIT_PURCHASABLE:
                System.out.print("Is game purchasable(y/n): ");
                if (reader.nextLine().trim().equalsIgnoreCase("y")) {
                    this.gameCopy.setPurchasable(true);
                } else if (reader.nextLine().trim().equalsIgnoreCase("n")) {
                    this.gameCopy.setPurchasable(false);
                }
                break;
            case SAVE:
                Game gameCopyBuild = gameCopy.build();
                Set<ConstraintViolation<Game>> violations = validator.validate(gameCopyBuild);
                if (violations.size() != 0) {
                    System.out.println("Invalid game object! Changes has not been saved!");
                    violations.forEach(v -> System.out.println(v.getMessage()));
                    return exitEditor();
                }
                String differences = game.differences(gameCopyBuild);
                if (!differences.isBlank()) {
                    gameService.save(gameCopyBuild);
                    System.out.println(differences);
                } else {
                    System.out.println("No changes made.");
                }
                return exitEditor();
            case CANCEL:
                return exitEditor();
        }
        return MenuType.EDIT_GAME_MENU;
    }

    private MenuType exitEditor() {
        this.game = null;
        this.gameCopy = null;
        return MenuType.ADMIN_MENU;
    }


    @Override
    public MenuType execute() {
        if (this.game == null) {
            gameService.printAllGame();
            this.game = gameService.selectGame();
        }
        return super.execute();
    }


}

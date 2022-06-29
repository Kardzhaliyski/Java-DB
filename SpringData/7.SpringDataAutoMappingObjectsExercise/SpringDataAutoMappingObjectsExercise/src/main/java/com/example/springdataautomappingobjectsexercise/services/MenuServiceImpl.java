package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.User;
import com.example.springdataautomappingobjectsexercise.models.enums.StartMenuOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    private static final String MENU_DELIMITER = " -------------------------------------------------- ";
    private static final String MENU_HALF_DELIMITER = " ------------------------- ";
    private static final String MENU_SIDE_DELIMITER = " --- ";
    private static final String MENU_ORDINAL_DELIMITER = " : ";

    private final StringBuilder stringBuilder;
    private final ReaderService sc;
    private final UserService userService;

    private User user;

    @Autowired
    public MenuServiceImpl(ReaderService sc, UserService userService) {
        this.stringBuilder = new StringBuilder();
        this.sc = sc;
        this.userService = userService;
    }

    private void startMenu() {
        printMenu();

        StartMenuOptions menu = null;


        while (menu == null) {
            try {
                System.out.print("Chose menu number: ");
                menu = StartMenuOptions.values()[Integer.parseInt(sc.nextLine()) - 1];
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input. Please try again.");
            }
        }
        switch (menu) {
            case LOGIN: {
                user = userService.login();
                break;
            } case REGISTER: {
                userService.register();
                startMenu();
                break;
            }
        }
    }

    public void mainMenu() {
        if(user == null) {
            System.out.println("You need to be logged in to see the menu.");
            return;
        }
//        printMainMenu();
    }



    @Override
    public void start() {
        startMenu();
    }

    private void printMenu() {
        stringBuilder.append(MENU_DELIMITER).append(System.lineSeparator());
        stringBuilder.append(MENU_SIDE_DELIMITER).append(" Menu ").append(MENU_SIDE_DELIMITER).append(System.lineSeparator());
        stringBuilder.append(MENU_HALF_DELIMITER).append(System.lineSeparator());
        for (StartMenuOptions value : StartMenuOptions.values()) {
            stringBuilder.append(MENU_SIDE_DELIMITER)
                    .append(value.ordinal() + 1)
                    .append(MENU_ORDINAL_DELIMITER)
                    .append(value.nameCapitalized())
                    .append(System.lineSeparator());
        }
        stringBuilder.append(MENU_DELIMITER).append(System.lineSeparator());
        System.out.println(stringBuilder);
        stringBuilder.setLength(0);
    }
}

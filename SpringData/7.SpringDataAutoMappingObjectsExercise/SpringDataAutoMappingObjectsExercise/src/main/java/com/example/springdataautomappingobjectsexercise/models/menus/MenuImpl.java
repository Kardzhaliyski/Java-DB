package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuDelimiter;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuType;
import com.example.springdataautomappingobjectsexercise.models.menus.options.MenuOption;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;


public abstract class MenuImpl implements Menu {
    private MenuOption[] menuOptions;
    protected ReaderService reader;

    private MenuImpl() {
    }

    protected MenuImpl(ReaderService reader, MenuOption[] menuOptions) {
        this.reader = reader;
        this.menuOptions = menuOptions;
    }

    public MenuType execute() {
        this.display(menuOptions);
        MenuOption menu = choseMenuOption(menuOptions);
        return this.executeMenuOption(menu);
    }

    protected void display(MenuOption[] menuOptions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MenuDelimiter.LONG_LINE).append(System.lineSeparator());
        stringBuilder.append(MenuDelimiter.SHORT_LINE).append(" Menu ").append(MenuDelimiter.SHORT_LINE).append(System.lineSeparator());
        stringBuilder.append(MenuDelimiter.LINE).append(System.lineSeparator());
        for (MenuOption value : menuOptions) {
            stringBuilder.append(MenuDelimiter.SHORT_LINE)
                    .append(value.ordinal() + 1)
                    .append(MenuDelimiter.MIDDLE_DELIMITER)
                    .append(value.nameCapitalized())
                    .append(System.lineSeparator());
        }
        stringBuilder.append(MenuDelimiter.LONG_LINE).append(System.lineSeparator());
        System.out.println(stringBuilder);
        stringBuilder.setLength(0);
    }

    protected abstract MenuType executeMenuOption(MenuOption menu);

    protected MenuOption choseMenuOption(MenuOption[] menuOptions) {
        MenuOption menuOption = null;

        while (menuOption == null) {
            try {
                System.out.print("Chose menu number: ");
                menuOption = menuOptions[Integer.parseInt(reader.nextLine()) - 1];
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input. Please try again.");
            }
        }

        return menuOption;
    }
}

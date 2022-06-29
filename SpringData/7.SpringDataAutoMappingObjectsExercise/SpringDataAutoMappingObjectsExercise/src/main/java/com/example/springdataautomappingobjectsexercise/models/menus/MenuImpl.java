package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.enums.MenuOption;


public abstract class MenuImpl implements Menu {
    protected MenuImpl() {
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
}

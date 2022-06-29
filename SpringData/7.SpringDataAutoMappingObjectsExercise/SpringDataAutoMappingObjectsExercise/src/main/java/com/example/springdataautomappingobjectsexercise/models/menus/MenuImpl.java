package com.example.springdataautomappingobjectsexercise.models.menus;

import com.example.springdataautomappingobjectsexercise.models.enums.MenuOptions;
import com.example.springdataautomappingobjectsexercise.models.enums.StartMenuOptions;


public class MenuImpl<T extends Enum<T> & MenuOptions> {
    protected MenuImpl() {
    }


    public void display(Enum<T> menu) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MenuDelimiter.LONG_LINE).append(System.lineSeparator());
        stringBuilder.append(MenuDelimiter.SHORT_LINE).append(" Menu ").append(MenuDelimiter.SHORT_LINE).append(System.lineSeparator());
        stringBuilder.append(MenuDelimiter.LINE).append(System.lineSeparator());
        for (StartMenuOptions value : StartMenuOptions.values()) {
            stringBuilder.append(MenuDelimiter.SHORT_LINE)
                    .append(value.ordinal() + 1)
                    .append(MenuDelimiter.MIDDLE_DELIMITER)
                    .append(value.nameCapitalized())
                    .append(System.lineSeparator());
        }
        stringBuilder.append(MenuDelimiter.LINE).append(System.lineSeparator());
        System.out.println(stringBuilder);
        stringBuilder.setLength(0);
    }

}

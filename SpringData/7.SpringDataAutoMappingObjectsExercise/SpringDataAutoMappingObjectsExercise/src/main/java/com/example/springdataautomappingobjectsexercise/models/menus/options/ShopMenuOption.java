package com.example.springdataautomappingobjectsexercise.models.menus.options;

public enum ShopMenuOption implements MenuOption {
    SHOW_CART,
    ADD_GAME_TO_CART,
    REMOVE_GAME_FROM_CART,
    ORDER;

    @Override
    public String menuName() {
        return "Shop Menu";
    }

    @Override
    public String nameCapitalized() {
        return MenuOption.super.nameCapitalized();
    }
}

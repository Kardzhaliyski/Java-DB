package com.example.springdataautomappingobjectsexercise.models.menus.options;

public enum ShopOption implements MenuOption {
    CHECK_CART,
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

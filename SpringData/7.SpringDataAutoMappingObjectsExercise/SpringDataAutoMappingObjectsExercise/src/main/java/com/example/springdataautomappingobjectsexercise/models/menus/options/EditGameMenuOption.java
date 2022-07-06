package com.example.springdataautomappingobjectsexercise.models.menus.options;

public enum EditGameMenuOption implements MenuOption {
    EDIT_TITLE,
    EDIT_PUBLISHER,
    EDIT_TRAILER_URL_ID,
    EDIT_THUMBNAIL_URL,
    EDIT_SIZE,
    EDIT_PRICE,
    EDIT_DESCRIPTION,
    EDIT_RELEASE_DATE,
    EDIT_PURCHASABLE,
    SAVE,
    CANCEL;


    //String title, Publisher publisher , String trailerURLId, String thumbnailUrl, Double size, BigDecimal price, String description, LocalDate releaseDate

    @Override
    public String menuName() {
        return "Edit Game Menu";
    }

    @Override
    public String nameCapitalized() {
        return MenuOption.super.nameCapitalized();
    }
}

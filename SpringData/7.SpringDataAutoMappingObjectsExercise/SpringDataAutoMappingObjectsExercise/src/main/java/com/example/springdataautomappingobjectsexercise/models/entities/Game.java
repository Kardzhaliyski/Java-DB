package com.example.springdataautomappingobjectsexercise.models.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Validated
@Entity(name = "games")
public class Game {

    public static class Builder {
        private final Game game;

        private Builder() {
            this.game = new Game();
        }

        public Game build() {
            if (game.getTitle() == null) {
                throw new IllegalStateException("Game title should not be null!");
            }
            if (game.getPublisher() == null) {
                throw new IllegalStateException("Game publisher should not be null!");
            }
            if (game.getPrice() == null) {
                throw new IllegalStateException("Game price should not be null!");
            }

            return game;
        }

        public Game.Builder duplicate(Game game) {
            this.game.setId(game.getId());
            setTitle(game.getTitle());
            setPublisher(game.getPublisher());
            setTrailerUrlId(game.getTrailerURLId());
            setThumbnailUrl(game.getThumbnailUrl());
            setSize(game.getSize());
            setPrice(game.getPrice());
            setDescription(game.getDescription());
            setReleaseDate(game.getReleaseDate());
            this.game.purchasable = game.getPurchasable();
            return this;
        }

        public Builder setTitle(String title) {
            game.setTitle(title);
            return this;
        }

        public String getTitle() {
            return game.getTitle();
        }

        public Builder setPublisher(Publisher publisher) {
            game.setPublisher(publisher);
            return this;
        }

        public Builder setTrailerUrlId(String id) {
            game.setTrailerURLId(id);
            return this;
        }

        public Builder setThumbnailUrl(String url) {
            game.setThumbnailUrl(url);
            return this;
        }

        public Builder setSize(Double size) {
            game.setSize(size);
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            game.setPrice(price);
            return this;
        }

        public BigDecimal getPrice() {
            return game.price;
        }

        public Builder setDescription(String description) {
            game.setDescription(description);
            return this;
        }

        public Builder setReleaseDate(LocalDate releaseDate) {
            game.setReleaseDate(releaseDate);
            return this;
        }
        public Builder setPurchasable(Boolean purchasable) {
            game.setPurchasable(purchasable);
            return this;
        }

        public Double getSize() {
            return game.getSize();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title should not be blank!")
    @Length(min = 2, message = "Game title should be at least 2 symbols!")
    @Column(nullable = false, unique = true)
    private String title;
    @NotNull(message = "Publisher should not be null!")
    @ManyToOne(optional = false)
    private Publisher publisher;
    @Length(min = 11, max = 11, message = "Trailer url id should be 11 symbols long!")
    @Column(name = "trailer_url_id", length = 11)
    private String trailerURLId;
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    @NotNull(message = "Size should not be null!")
    @Positive(message = "Size should be positive number!")
    private Double size;
    @NotNull(message = "Price should not be null!")
    @Positive(message = "Price should be positive number!")
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @NotNull
    private Boolean purchasable;

    protected Game() {
        purchasable = true;
    }

    public Game(String title, Publisher publisher, String trailerURLId, String thumbnailUrl, Double size, BigDecimal price, String description, LocalDate releaseDate) {
        setTitle(title);
        setPublisher(publisher);
        setTrailerURLId(trailerURLId);
        setThumbnailUrl(thumbnailUrl);
        setSize(size);
        setPrice(price);
        setDescription(description);
        setReleaseDate(releaseDate);
        this.purchasable = true;
    }


    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
//        if (title == null || title.isBlank()) {
//            throw new IllegalArgumentException("Game title should not be null!");
//        }
        this.title = title.trim();
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
//        if (publisher == null) {
//            throw new IllegalArgumentException("Publisher should not be Null!");
//        }
        this.publisher = publisher;
    }

    public String getTrailerURLId() {
        return trailerURLId;
    }

    private void setTrailerURLId(String trailerURLId) {
        if (trailerURLId.isBlank()) {
            this.trailerURLId = null;
            return;
        }
        this.trailerURLId = trailerURLId.trim();
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    private void setThumbnailUrl(String thumbnailUrl) {
        if (thumbnailUrl.isBlank()) {
            this.thumbnailUrl = null;
            return;
        }
        this.thumbnailUrl = thumbnailUrl.trim();
    }

    public Double getSize() {
        return size;
    }

    private void setSize(Double size) {
//        if (size == null) {
//            this.size = null;
//            return;
//        }
//        if (size <= 0) {
//            throw new IllegalArgumentException("Game size should be positive number!");
//        }
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
//        if (price == null) {
//            throw new IllegalArgumentException("Game price should not be null!");
//        }
//
//        if (price.max(BigDecimal.ZERO).equals(BigDecimal.ZERO)) {
//            throw new IllegalArgumentException("Game price should be positive number!");
//        }

        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if (description != null && description.isBlank()) {
            this.description = null;
            return;
        }
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    private void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getPurchasable() {
        return purchasable;
    }

    private void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        return getTitle().equals(game.getTitle());
    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }

    @Override
    public String toString() {
        return "Game id: " + id +
                ", " + title +
                ", Publisher: " + publisher +
                ", Release Date: " + releaseDate +
                ", Price: " + price +
                (description == null ? "" : (", Description: " + description));
    }

    public String differences(Game game) {
        StringBuilder sb = new StringBuilder();
        if (!this.getTitle().equals(game.getTitle())) {
            sb.append(String.format(
                            "Title \"%s\" changed to \"%s\"",
                            this.getTitle(),
                            game.getTitle()))
                    .append(System.lineSeparator());
        }
        if (!this.getPublisher().equals(game.getPublisher())) {
            sb.append(String.format(
                            "Publisher \"%s\" changed to \"%s\"",
                            this.getPublisher().getName(),
                            game.getPublisher().getName()))
                    .append(System.lineSeparator());
        }
        if (!this.getTrailerURLId().equals(game.getTrailerURLId())) {
            sb.append(String.format(
                            "Trailer URL Id \"%s\" changed to \"%s\"",
                            this.getTrailerURLId(),
                            game.getTrailerURLId()))
                    .append(System.lineSeparator());
        }

        if (!this.getThumbnailUrl().equals(game.getThumbnailUrl())) {
            sb.append(String.format(
                            "Trailer thumbnail URL \"%s\" changed to \"%s\"",
                            this.getThumbnailUrl(),
                            game.getThumbnailUrl()))
                    .append(System.lineSeparator());
        }

        if (!this.getSize().equals(game.getSize())) {
            sb.append(String.format(
                            "Size \"%s\" changed to \"%s\"",
                            this.getSize(),
                            game.getSize()))
                    .append(System.lineSeparator());
        }

        if (!this.getPrice().equals(game.getPrice())) {
            sb.append(String.format(
                            "Price \"%s\" changed to \"%s\"",
                            this.getPrice(),
                            game.getPrice()))
                    .append(System.lineSeparator());
        }

        if (this.getDescription() != null &&
                !this.getDescription().equals(game.getDescription())) {
            sb.append(String.format(
                            "Description \"%s\" changed to \"%s\"",
                            this.getDescription(),
                            game.getDescription()))
                    .append(System.lineSeparator());
        }
        if (!this.getReleaseDate().equals(game.getReleaseDate())) {
            sb.append(String.format(
                            "Release Date \"%s\" changed to \"%s\"",
                            this.getReleaseDate(),
                            game.getReleaseDate()))
                    .append(System.lineSeparator());
        }
        if (!this.getPurchasable().equals(game.getPurchasable())) {
            sb.append(String.format(
                            "Purchasable \"%s\" changed to \"%s\"",
                            this.getPurchasable(),
                            game.getPurchasable()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();

    }
}

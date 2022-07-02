package com.example.springdataautomappingobjectsexercise.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "games")
public class Game {

    public static class Builder {
        private final Game game;

        private Builder() {
            this.game = new Game();
        }

        public Game build() {
            if(game.getTitle() == null) {
                throw new IllegalStateException("Game title should not be null!");
            }
            if(game.getPublisher() == null) {
                throw new IllegalStateException("Game publisher should not be null!");
            }
            if(game.getPrice() == null) {
                throw new IllegalStateException("Game price should not be null!");
            }

            return game;
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

        public Double getSize() {
            return game.getSize();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;
    @ManyToOne(optional = false)
    private Publisher publisher;
    @Column(name = "trailer_url_id", length = 11)
    private String trailerURLId;
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    private Double size;
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private Boolean purchasable;

    protected Game() {
        purchasable = true;
    }

    public Game(String title, Publisher publisher , String trailerURLId, String thumbnailUrl, Double size, BigDecimal price, String description, LocalDate releaseDate) {
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
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Game title should not be null!");
        }
        this.title = title.trim();
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        if(publisher == null) {
            throw new IllegalArgumentException("Publisher should not be Null!");
        }
        this.publisher = publisher;
    }

    public String getTrailerURLId() {
        return trailerURLId;
    }

    private void setTrailerURLId(String trailerURLId) {
        if(trailerURLId.isBlank()) {
            this.trailerURLId = null;
            return;
        }
        this.trailerURLId = trailerURLId.trim();
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    private void setThumbnailUrl(String thumbnailUrl) {
        if(thumbnailUrl.isBlank()){
            this.thumbnailUrl = null;
            return;
        }
        this.thumbnailUrl = thumbnailUrl.trim();
    }

    public Double getSize() {
        return size;
    }

    private void setSize(Double size) {
        if(size == null) {
            this.size = null;
            return;
        }
        if(size <= 0) {
            throw new IllegalArgumentException("Game size should be positive number!");
        }
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        if(price == null) {
            throw new IllegalArgumentException("Game price should not be null!");
        }

        if(price.max(BigDecimal.ZERO).equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Game price should be positive number!");
        }

        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if(description != null && description.isBlank()) {
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


}

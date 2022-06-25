package com.example.springdataautomappingobjectsexercise.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;
    @ManyToOne
    private Publisher publisher;
    @Column(name = "trailer_url_id")
    private String trailerURLId;
    @Column(name = "thumbnail_url", length = 11)
    private String thumbnailUrl;
    private double size;
    @Column(nullable = false)
    private BigDecimal price;
    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private Boolean puchasable;

    protected Game() {
    }

    public Game(String title, String trailerURLId, String thumbnailUrl, double size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailerURLId = trailerURLId;
        this.thumbnailUrl = thumbnailUrl;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
        this.puchasable = true;
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
        this.title = title;
    }

    public String getTrailerURLId() {
        return trailerURLId;
    }

    private void setTrailerURLId(String trailerURLId) {
        this.trailerURLId = trailerURLId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    private void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public double getSize() {
        return size;
    }

    private void setSize(double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    private void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getPuchasable() {
        return puchasable;
    }

    private void setPuchasable(Boolean puchasable) {
        this.puchasable = puchasable;
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

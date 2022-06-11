package bookshopsystem.models;

import bookshopsystem.models.enums.EditionType;
import bookshopsystem.models.enums.AgeRestriction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000, nullable = true)
    private String description;

    @Column(name = "edition_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int copies;

    @Column(name = "release_date", nullable = true)
    private LocalDate releaseDate;

    @Column(name = "age_restriction",nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne(optional = false)
    private Author author;

    @ManyToMany
    private Set<Category> categiries;

    protected Book() {
        this.categiries = new HashSet<>();
    }

    public Book(String title, EditionType editionType, BigDecimal price, int copies, AgeRestriction ageRestriction, Author author) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categiries = new HashSet<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    private void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    private void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    private void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    private void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategiries() {
        return Collections.unmodifiableSet(categiries);
    }

    private void setCategiries(Set<Category> categiries) {
        this.categiries = categiries;
    }

    public void addCategirie (Category category) {
        if(category != null){
            categiries.add(category);
        } else {
            throw new IllegalArgumentException("Category should not be null!");
        }
    }
}

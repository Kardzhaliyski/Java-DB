package bookshopsystem.models.user;

import javax.persistence.*;

@Entity(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    protected Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Country name should not be null or blank!");
        }
        this.name = name;
    }
}

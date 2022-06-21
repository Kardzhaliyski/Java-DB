package bookshopsystem.models.user;

import javax.persistence.*;

@Entity(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Country country;

    protected Town() {
    }

    public Town(String name, Country country) {
        this.name = name;
        this.country = country;
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
            throw new IllegalArgumentException("Town name should not be null or blank!");
        }

        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    private void setCountry(Country country) {
        this.country = country;
    }
}

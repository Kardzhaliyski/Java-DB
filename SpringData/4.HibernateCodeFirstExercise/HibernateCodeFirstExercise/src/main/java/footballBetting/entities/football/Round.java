package footballBetting.entities.football;

import javax.persistence.*;

@Entity(name = "round")

public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected Round() {
    }

    public Round(String name) {
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
        this.name = name;
    }
}
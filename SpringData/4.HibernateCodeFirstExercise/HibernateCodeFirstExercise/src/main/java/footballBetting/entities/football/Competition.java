package footballBetting.entities.football;

import javax.persistence.*;

@Entity(name = "competition")

public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type_id")
    private CompetitionType competitionType;

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    protected Competition() {
    }

    public Competition(String name, CompetitionType competitionType) {
        this.name = name;
        this.competitionType = competitionType;
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
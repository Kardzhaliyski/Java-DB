package footballBetting.entities.football;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "positions")
public class Position {

    @Id
    @Column(length = 2, unique = true)
    private String id;
    @Column(name = "position_description")
    private String description;

    protected Position() {
    }

    public Position(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }
}

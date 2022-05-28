package hospital.entities;

import javax.persistence.*;

@Entity(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    protected Diagnose() {
    }

    public Diagnose(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Diagnose name must not be Null or Empty!");
        }
        this.name = name;
    }
}

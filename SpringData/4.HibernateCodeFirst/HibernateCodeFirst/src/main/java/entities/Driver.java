package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "full_name")
    private String fullName;

    @ManyToMany(mappedBy = "drivers")
    List<Truck> trucks;

    protected Driver() {
    }

    public Driver(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}

package hospital.entities;

import javax.persistence.*;

@Entity(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double quantity;
    private String comment;

    protected Medication() {
    }

    public Medication(String name, double quantity) {
        setName(name);
        setQuantity(quantity);
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
            throw new IllegalArgumentException("Medication name must not be Null or Empty!");
        }
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    private void setQuantity(double quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Medication quantity must be positive number!");
        }
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

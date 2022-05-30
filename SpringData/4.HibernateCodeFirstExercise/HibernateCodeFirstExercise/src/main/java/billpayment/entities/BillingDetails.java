package billpayment.entities;

import javax.persistence.*;

@Entity(name = "billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User owner;

    protected BillingDetails() {
    }

    protected BillingDetails(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private User getOwner() {
        return owner;
    }

    private void setOwner(User owner) {
        this.owner = owner;
    }
}

package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "planes")
public class Plane extends Vehicle {
    public static final String VEHICLE_TYPE = "Plane";
    private String airline;
    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @ManyToOne
    @JoinColumn(name = "company_id",referencedColumnName = "id")
    private Company company;

    protected Plane() {
    }

    public Plane(String model, BigDecimal price, String fuelType, String airline, int passengerCapacity) {
        super(VEHICLE_TYPE, model, price, fuelType);
        this.airline = airline;
        this.passengerCapacity = passengerCapacity;
    }

    public String getAirline() {
        return airline;
    }

    private void setAirline(String airline) {
        this.airline = airline;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    private void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

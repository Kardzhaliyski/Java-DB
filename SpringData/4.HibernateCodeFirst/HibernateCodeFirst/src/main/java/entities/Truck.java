package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "trucks")
public class Truck extends Vehicle{
    public static final String VEHICLE_TYPE = "Truck";
    @Column(name = "load_capacity")
    private double loadCapacity;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "truck_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    Set<Driver> drivers;

    protected Truck() {
    }

    public Truck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(VEHICLE_TYPE, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }
}

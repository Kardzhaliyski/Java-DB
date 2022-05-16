package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "trucks")
public class Truck extends Vehicle{
    public static final String VEHICLE_TYPE = "Truck";
    @Column(name = "load_capacity")
    private double loadCapacity;

    protected Truck() {
    }

    public Truck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(VEHICLE_TYPE, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }
}

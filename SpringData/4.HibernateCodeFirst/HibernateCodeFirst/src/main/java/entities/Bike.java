package entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "bikes")
public class Bike extends Vehicle {
    public static final String VEHICLE_TYPE = "bike";
    protected Bike() {
    }

    public Bike(String model, BigDecimal price, String fuelType) {
        super(VEHICLE_TYPE, model, price, fuelType);
    }
}

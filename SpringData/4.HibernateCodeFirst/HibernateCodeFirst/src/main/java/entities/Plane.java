package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "planes")
public class Plane extends Vehicle {
    public static final String VEHICLE_TYPE = "Plane";
    String airline;
    @Column(name = "passenger_capacity")
     int passengerCapacity;

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

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}

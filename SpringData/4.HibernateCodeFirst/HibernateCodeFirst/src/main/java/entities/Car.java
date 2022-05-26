package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Car extends Vehicle{
    public static final String VEHICLE_TYPE = "Car";

    private int seats;
    @OneToOne
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private PlateNumber plateNumber;

    protected Car() {
    }

    public Car(String model, BigDecimal price, String fuelType, int seats, PlateNumber plateNumber) {
        super(VEHICLE_TYPE, model, price, fuelType);
        this.seats = seats;
        this.plateNumber = plateNumber;
    }

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}

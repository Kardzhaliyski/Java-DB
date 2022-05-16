package entities;

import javax.persistence.*;

@Entity
@Table(name = "plate_number")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    protected PlateNumber() {
    }

    public PlateNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    String planeNumber;

    @OneToOne(mappedBy = "plateNumber")
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }
}
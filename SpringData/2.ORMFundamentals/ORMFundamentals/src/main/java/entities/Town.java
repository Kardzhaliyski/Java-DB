package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;


@Entity(name = "towns")
public class Town {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "population")
    private int population;
    @Column(name = "country")
    private String country;

    private Town() {};

    public Town(String name, int population, String country) {
        this.name = name;
        this.population = population;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " " + population + " " + country;
    }
}

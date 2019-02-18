package org.launchcode.gardenbox.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GardenBox {

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany
    private List<Plant> plants = new ArrayList<Plant>();

    public GardenBox(){

    }

    public List<Plant> getPlants() {
        return plants;
    }

    private int getId(){return id; }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void addPlant(Plant newPlant) {
        plants.add(newPlant);
    }
}

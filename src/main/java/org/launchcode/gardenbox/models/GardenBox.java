package org.launchcode.gardenbox.models;

import java.util.ArrayList;
import java.util.List;


public class GardenBox {

    private List<Plant> plants = new ArrayList<Plant>();

    public GardenBox(){

    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void addPlant(Plant newPlant) {
        plants.add(newPlant);
    }
}

package org.launchcode.gardenbox.models;

import javax.persistence.*;
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

    public List<Plant> getCompanionPlants(){

        List<Plant> allCompanionPlants = new ArrayList<Plant>();

        List<Plant> allAvoidedPlants = this.getAvoidedPlants();

        List<Plant> allPlants = this.getPlants();

        for (Plant plant : allPlants){
            List<Plant> companionPlants = plant.getCompanionPlants();
            for (Plant companionPlant : companionPlants) {
                if (allCompanionPlants.contains(companionPlant)) {
                    continue;
                }
                allCompanionPlants.add(companionPlant);
                if (allAvoidedPlants.contains(companionPlant)) {
                    allCompanionPlants.remove(companionPlant);
                }
            }
        }

        return allCompanionPlants;
    }

    public List<Plant> getAvoidedPlants(){

        List<Plant> allAvoidedPlants = new ArrayList<Plant>();

        List<Plant> allPlants = this.getPlants();

        for (Plant plant : allPlants){
            List<Plant> avoidedPlants = plant.getAvoidedPlants();
            for (Plant avoidedPlant : avoidedPlants) {
                if (allAvoidedPlants.contains(avoidedPlant)) {
                    continue;
                }
                allAvoidedPlants.add(avoidedPlant);
            }
        }
        return allAvoidedPlants;
    }
}

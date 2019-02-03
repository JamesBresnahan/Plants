package org.launchcode.gardenbox.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Plant {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToOne
    private PlantType type;

    @ManyToMany
    private List<Plant> companionPlants;

    @ManyToMany
    private List<Plant> avoidedPlants;




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }

    public List<Plant> getCompanionPlants() {
        return companionPlants;
    }

    public void addCompanionPlant(Plant companionPlant) {
        companionPlants.add(companionPlant);
    }

    public List<Plant> getAvoidedPlants() {
        return avoidedPlants;
    }

    public void addAvoidedPlants(Plant avoidedPlant) {
        companionPlants.add(avoidedPlant);
    }
}

package org.launchcode.gardenbox.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plant {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    private PlantType type;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Plant> companionPlants = new ArrayList<Plant>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Plant> avoidedPlants= new ArrayList<Plant>();

    public List<Plant> getCompanionPlants() {
        return companionPlants;
    }

    public void addCompanionPlants(Plant companion) {
        companionPlants.add(companion);
    }

    public void setCompanionPlants(List<Plant> companionPlants){this.companionPlants = companionPlants;}

    public List<Plant> getAvoidedPlants() {
        return avoidedPlants;
    }

    public void addAvoidedPlants(Plant avoided) {avoidedPlants.add(avoided);}

    public void setAvoidedPlants(List<Plant> avoidedPlants) {
        this.avoidedPlants = avoidedPlants;
    }

    public Plant (){

    }

    public Plant (PlantType type){
        this.type= type;
    }

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

}

package org.launchcode.gardenbox.models.forms;

import org.launchcode.gardenbox.models.Plant;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UpdatePlantForm {

    //@NotNull
    private Plant plant;

    @NotNull
    private int plantId;

   // private List<Plant> companionPlants = new ArrayList<>();

    public UpdatePlantForm(){

    }

    public UpdatePlantForm(int plantId){
        this.plantId=plantId;
    }

    //public List<Plant> getCompanionPlants() {
    //    return companionPlants;
    //}

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    //public void setCompanionPlants(List<Plant> companionPlants) {
    //    this.companionPlants = companionPlants;
    //}

    public List<Plant> getAvoidedPlants() {
        return avoidedPlants;
    }

    public void setAvoidedPlants(List<Plant> avoidedPlants) {
        this.avoidedPlants = avoidedPlants;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    private List<Plant> avoidedPlants = new ArrayList<>();



}

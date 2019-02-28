package org.launchcode.gardenbox.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class GardenBox {


    private int id;

    private List<Plant> plants = new ArrayList<Plant>();

    private List<Plant> allCompanionPlants = new ArrayList<Plant>();

    private List<Plant> allAvoidedPlants = new ArrayList<Plant>();


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

    public void clearGardenBox(){
        plants.clear();
    }

    public List<Integer> getCompanionPlantsIds(){
        List<Integer> allCompanionPlantsIds = new ArrayList<>();
        for(Plant companionPlant: allCompanionPlants){
            allCompanionPlantsIds.add(companionPlant.getId());
        }
        return allCompanionPlantsIds;
    }

    public List<Integer> getAvoidedPlantsIds(){
        List<Integer> allAvoidedPlantsIds = new ArrayList<>();
        for(Plant avoidedPlant: allAvoidedPlants){
            allAvoidedPlantsIds.add(avoidedPlant.getId());
        }
        return allAvoidedPlantsIds;
    }

    public List<Plant> getCompanionPlants(){

        List<Plant> allPlants = this.getPlants();
        List<Integer> allAvoidedPlantsIds=this.getAvoidedPlantsIds();
        List<Integer> allCompanionPlantsIds = this.getCompanionPlantsIds();
        for (Plant plant : allPlants){
            List<Plant> companionPlants = plant.getCompanionPlants();

            for (Plant companionPlant : companionPlants) {
                if (this.getAvoidedPlantsIds().contains(companionPlant.getId())) {
                    allCompanionPlants.remove(companionPlant);
                    continue;
                }

                if(this.getCompanionPlantsIds().contains(companionPlant.getId())){
                    continue;
                }

                allCompanionPlants.add(companionPlant);

            }
        }

        return allCompanionPlants;
    }

    public List<Plant> getAvoidedPlants(){


        List<Plant> allPlants = this.getPlants();

        for (Plant plant : allPlants){
            List<Integer> allAvoidedPlantsIds = this.getAvoidedPlantsIds();
            List<Plant> avoidedPlants = plant.getAvoidedPlants();
            for (Plant avoidedPlant : avoidedPlants) {
                if (this.getAvoidedPlantsIds().contains(avoidedPlant.getId())) {
                    continue;
                }
                allAvoidedPlants.add(avoidedPlant);
            }
        }
        return allAvoidedPlants;
    }
}

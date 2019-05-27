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

    public void removePlant(Plant newPlant) {plants.remove(newPlant);}


    public void clearGardenBox(){
        plants.clear();
    }

    public void clearCompanionPlants(){allCompanionPlants.clear();}

    public void clearAvoidedPlants(){allAvoidedPlants.clear();}

    public void removeCompanionPlants(List<Integer>removeIds){
        //use a workaround to avoid a Concurrent Modification Exception
        List<Plant>toRemove = new ArrayList<>();

        for (Plant plant:allCompanionPlants){
            if(removeIds.contains(plant.getId())){
                toRemove.add(plant);
            }
        }
        allCompanionPlants.removeAll(toRemove);
    }


    public List<Integer> getPlantIds(){
        List<Plant> allPlants = this.getPlants();
        List<Integer> allPlantIds = new ArrayList<>();
        for (Plant plant: allPlants){
            allPlantIds.add(plant.getId());
        }
        return allPlantIds;
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

        //add companion plants, filter out any companions that are avoided of something in planter box, do not list same companion multiple times
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

        List<Integer> toRemove = new ArrayList<>();

        //Remove plant from suggested companion if something in planter box is one of its avoideds
        for (Plant companionPlant:allCompanionPlants){
            List<Plant> avoidedPlants = companionPlant.getAvoidedPlants();
            List<Integer> plantIds = this.getPlantIds();
            for (Plant avoidedPlant:avoidedPlants){
                if(plantIds.contains(avoidedPlant.getId())){
                    //use a workaround to avoid a Concurrent Modification Exception
                    toRemove.add(companionPlant.getId());
                }

            }
        }
        removeCompanionPlants(toRemove);

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

package org.launchcode.gardenbox.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public enum PlantType {

    VEGETABLE ("Vegetable"),
    FRUIT ("Fruit"),
    HERB ("Herb");


    private final String name;

    PlantType(String name){
        this.name =name;
    }


    public String getName(){
        return name;

    }

}

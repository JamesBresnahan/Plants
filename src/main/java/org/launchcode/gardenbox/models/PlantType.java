package org.launchcode.gardenbox.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public enum PlantType {

    VEGETABLE ("Vegetable"),
    FRUIT ("Fruit"),
    HEB ("Herb");

    @Id
    @GeneratedValue
    private int id;

    private final String name;

    PlantType(String name){
        this.name =name;
    }


    public String getName(){
        return name;

    }

}

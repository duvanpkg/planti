package com.example.planti.entidad;

import java.io.Serializable;

public class Planta implements Serializable {
    int idPlant;
    String name;
    String plantKind;
    String description;

    // constructor

    public Planta(int idPlant, String name, String plantKind, String description) {
        this.idPlant = idPlant;
        this.name = name;
        this.plantKind = plantKind;
        this.description = description;
    }

    // getters and setters

    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlantKind() {
        return plantKind;
    }

    public void setPlantKind(String plantKind) {
        this.plantKind = plantKind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

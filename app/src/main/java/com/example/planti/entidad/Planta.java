package com.example.planti.entidad;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Planta implements Serializable {
    int idPlant;
    String name;
    String plantKind;
    Bitmap image;
    String description;

    // constructor

    public Planta(int idPlant, String name, String plantKind, Bitmap image, String description) {
        this.idPlant = idPlant;
        this.name = name;
        this.plantKind = plantKind;
        this.image = image;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

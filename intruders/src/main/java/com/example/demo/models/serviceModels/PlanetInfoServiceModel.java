package com.example.demo.models.serviceModels;

import javax.persistence.Column;

public class PlanetInfoServiceModel {
    private long id;
    private int metalCapacity;
    private int gasCapacity;
    private int diamondCapacity;
    private int energyCapacity;
    private int metalForMin;
    private int gasForMin;
    private int diamondForMin;
    private int energyForMin;
    private String name;
    private String description;

    public long getId() {
        return id;
    }

    public PlanetInfoServiceModel setId(long id) {
        this.id = id;
        return this;
    }

    public int getMetalCapacity() {
        return metalCapacity;
    }

    public PlanetInfoServiceModel setMetalCapacity(int metalCapacity) {
        this.metalCapacity = metalCapacity;
        return this;
    }

    public int getGasCapacity() {
        return gasCapacity;
    }

    public PlanetInfoServiceModel setGasCapacity(int gasCapacity) {
        this.gasCapacity = gasCapacity;
        return this;
    }

    public int getDiamondCapacity() {
        return diamondCapacity;
    }

    public PlanetInfoServiceModel setDiamondCapacity(int diamondCapacity) {
        this.diamondCapacity = diamondCapacity;
        return this;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public PlanetInfoServiceModel setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
        return this;
    }

    public int getMetalForMin() {
        return metalForMin;
    }

    public PlanetInfoServiceModel setMetalForMin(int metalForMin) {
        this.metalForMin = metalForMin;
        return this;
    }

    public int getGasForMin() {
        return gasForMin;
    }

    public PlanetInfoServiceModel setGasForMin(int gasForMin) {
        this.gasForMin = gasForMin;
        return this;
    }

    public int getDiamondForMin() {
        return diamondForMin;
    }

    public PlanetInfoServiceModel setDiamondForMin(int diamondForMin) {
        this.diamondForMin = diamondForMin;
        return this;
    }

    public int getEnergyForMin() {
        return energyForMin;
    }

    public PlanetInfoServiceModel setEnergyForMin(int energyForMin) {
        this.energyForMin = energyForMin;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlanetInfoServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlanetInfoServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
}

package com.example.demo.models.serviceModels;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


public class PlanetResourceServiceModel {
    private long id;
    private int metalCapacity;
    private int gasCapacity;
    private int diamondCapacity;
    private int energyCapacity;
    private int metalForMin;
    private int gasForMin;
    private int diamondForMin;
    private int energyForMin;
    private int metalOwn;
    private int gasOwn;
    private int diamondOwn;
    private int energyOwn;

    public long getId() {
        return id;
    }

    public PlanetResourceServiceModel setId(long id) {
        this.id = id;
        return this;
    }

    public int getMetalCapacity() {
        return metalCapacity;
    }

    public PlanetResourceServiceModel setMetalCapacity(int metalCapacity) {
        this.metalCapacity = metalCapacity;
        return this;
    }

    public int getGasCapacity() {
        return gasCapacity;
    }

    public PlanetResourceServiceModel setGasCapacity(int gasCapacity) {
        this.gasCapacity = gasCapacity;
        return this;
    }

    public int getDiamondCapacity() {
        return diamondCapacity;
    }

    public PlanetResourceServiceModel setDiamondCapacity(int diamondCapacity) {
        this.diamondCapacity = diamondCapacity;
        return this;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public PlanetResourceServiceModel setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
        return this;
    }

    public int getMetalForMin() {
        return metalForMin;
    }

    public PlanetResourceServiceModel setMetalForMin(int metalForMin) {
        this.metalForMin = metalForMin;
        return this;
    }

    public int getGasForMin() {
        return gasForMin;
    }

    public PlanetResourceServiceModel setGasForMin(int gasForMin) {
        this.gasForMin = gasForMin;
        return this;
    }

    public int getDiamondForMin() {
        return diamondForMin;
    }

    public PlanetResourceServiceModel setDiamondForMin(int diamondForMin) {
        this.diamondForMin = diamondForMin;
        return this;
    }

    public int getEnergyForMin() {
        return energyForMin;
    }

    public PlanetResourceServiceModel setEnergyForMin(int energyForMin) {
        this.energyForMin = energyForMin;
        return this;
    }

    public int getMetalOwn() {
        return metalOwn;
    }

    public PlanetResourceServiceModel setMetalOwn(int metalOwn) {
        this.metalOwn = metalOwn;
        return this;
    }

    public int getGasOwn() {
        return gasOwn;
    }

    public PlanetResourceServiceModel setGasOwn(int gasOwn) {
        this.gasOwn = gasOwn;
        return this;
    }

    public int getDiamondOwn() {
        return diamondOwn;
    }

    public PlanetResourceServiceModel setDiamondOwn(int diamondOwn) {
        this.diamondOwn = diamondOwn;
        return this;
    }

    public int getEnergyOwn() {
        return energyOwn;
    }

    public PlanetResourceServiceModel setEnergyOwn(int energyOwn) {
        this.energyOwn = energyOwn;
        return this;
    }
}

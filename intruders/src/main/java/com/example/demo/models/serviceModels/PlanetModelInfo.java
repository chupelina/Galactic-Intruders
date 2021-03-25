package com.example.demo.models.serviceModels;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class PlanetModelInfo {
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
    private String name;
    private String imgUrl;
    private String description;

    public long getId() {
        return id;
    }

    public PlanetModelInfo setId(long id) {
        this.id = id;
        return this;
    }

    public int getMetalCapacity() {
        return metalCapacity;
    }

    public PlanetModelInfo setMetalCapacity(int metalCapacity) {
        this.metalCapacity = metalCapacity;
        return this;
    }

    public int getGasCapacity() {
        return gasCapacity;
    }

    public PlanetModelInfo setGasCapacity(int gasCapacity) {
        this.gasCapacity = gasCapacity;
        return this;
    }

    public int getDiamondCapacity() {
        return diamondCapacity;
    }

    public PlanetModelInfo setDiamondCapacity(int diamondCapacity) {
        this.diamondCapacity = diamondCapacity;
        return this;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public PlanetModelInfo setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
        return this;
    }

    public int getMetalForMin() {
        return metalForMin;
    }

    public PlanetModelInfo setMetalForMin(int metalForMin) {
        this.metalForMin = metalForMin;
        return this;
    }

    public int getGasForMin() {
        return gasForMin;
    }

    public PlanetModelInfo setGasForMin(int gasForMin) {
        this.gasForMin = gasForMin;
        return this;
    }

    public int getDiamondForMin() {
        return diamondForMin;
    }

    public PlanetModelInfo setDiamondForMin(int diamondForMin) {
        this.diamondForMin = diamondForMin;
        return this;
    }

    public int getEnergyForMin() {
        return energyForMin;
    }

    public PlanetModelInfo setEnergyForMin(int energyForMin) {
        this.energyForMin = energyForMin;
        return this;
    }

    public int getMetalOwn() {
        return metalOwn;
    }

    public PlanetModelInfo setMetalOwn(int metalOwn) {
        this.metalOwn = metalOwn;
        return this;
    }

    public int getGasOwn() {
        return gasOwn;
    }

    public PlanetModelInfo setGasOwn(int gasOwn) {
        this.gasOwn = gasOwn;
        return this;
    }

    public int getDiamondOwn() {
        return diamondOwn;
    }

    public PlanetModelInfo setDiamondOwn(int diamondOwn) {
        this.diamondOwn = diamondOwn;
        return this;
    }

    public int getEnergyOwn() {
        return energyOwn;
    }

    public PlanetModelInfo setEnergyOwn(int energyOwn) {
        this.energyOwn = energyOwn;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlanetModelInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PlanetModelInfo setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlanetModelInfo setDescription(String description) {
        this.description = description;
        return this;
    }
}

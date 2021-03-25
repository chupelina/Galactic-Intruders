package com.example.demo.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "planet_resources")
public class PlanetResourceEntity extends BaseEntity {
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

    public int getMetalCapacity() {
        return metalCapacity;
    }

    public PlanetResourceEntity setMetalCapacity(int metalCapacity) {
        this.metalCapacity = metalCapacity;
        return this;
    }

    public int getGasCapacity() {
        return gasCapacity;
    }

    public PlanetResourceEntity setGasCapacity(int gasCapacity) {
        this.gasCapacity = gasCapacity;
        return this;
    }

    public int getDiamondCapacity() {
        return diamondCapacity;
    }

    public PlanetResourceEntity setDiamondCapacity(int diamondCapacity) {
        this.diamondCapacity = diamondCapacity;
        return this;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public PlanetResourceEntity setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
        return this;
    }

    public int getMetalForMin() {
        return metalForMin;
    }

    public PlanetResourceEntity setMetalForMin(int metalForMin) {
        this.metalForMin = metalForMin;
        return this;
    }

    public int getGasForMin() {
        return gasForMin;
    }

    public PlanetResourceEntity setGasForMin(int gasForMin) {
        this.gasForMin = gasForMin;
        return this;
    }

    public int getDiamondForMin() {
        return diamondForMin;
    }

    public PlanetResourceEntity setDiamondForMin(int diamondForMin) {
        this.diamondForMin = diamondForMin;
        return this;
    }

    public int getEnergyForMin() {
        return energyForMin;
    }

    public PlanetResourceEntity setEnergyForMin(int energyForMin) {
        this.energyForMin = energyForMin;
        return this;
    }

    public int getMetalOwn() {
        return metalOwn;
    }

    public PlanetResourceEntity setMetalOwn(int metalOwn) {
        this.metalOwn = metalOwn;
        return this;
    }

    public int getGasOwn() {
        return gasOwn;
    }

    public PlanetResourceEntity setGasOwn(int gasOwn) {
        this.gasOwn = gasOwn;
        return this;
    }

    public int getDiamondOwn() {
        return diamondOwn;
    }

    public PlanetResourceEntity setDiamondOwn(int diamondOwn) {
        this.diamondOwn = diamondOwn;
        return this;
    }

    public int getEnergyOwn() {
        return energyOwn;
    }

    public PlanetResourceEntity setEnergyOwn(int energyOwn) {
        this.energyOwn = energyOwn;
        return this;
    }
}

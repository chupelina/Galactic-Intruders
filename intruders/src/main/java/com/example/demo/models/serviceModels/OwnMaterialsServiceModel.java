package com.example.demo.models.serviceModels;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class OwnMaterialsServiceModel {
    private int metalOwn;
    private int gasOwn;
    private int diamondOwn;
    private int energyOwn;
    private String image;
    private long planetId;

    public long getPlanetId() {
        return planetId;
    }

    public OwnMaterialsServiceModel setPlanetId(long planetId) {
        this.planetId = planetId;
        return this;
    }

    public String getImage() {
        return image;
    }

    public OwnMaterialsServiceModel setImage(String image) {
        this.image = image;
        return this;
    }

    public int getMetalOwn() {
        return metalOwn;
    }

    public OwnMaterialsServiceModel setMetalOwn(int metalOwn) {
        this.metalOwn = metalOwn;
        return this;
    }

    public int getGasOwn() {
        return gasOwn;
    }

    public OwnMaterialsServiceModel setGasOwn(int gasOwn) {
        this.gasOwn = gasOwn;
        return this;
    }

    public int getDiamondOwn() {
        return diamondOwn;
    }

    public OwnMaterialsServiceModel setDiamondOwn(int diamondOwn) {
        this.diamondOwn = diamondOwn;
        return this;
    }

    public int getEnergyOwn() {
        return energyOwn;
    }

    public OwnMaterialsServiceModel setEnergyOwn(int energyOwn) {
        this.energyOwn = energyOwn;
        return this;
    }

    @Override
    public String toString() {
        return "OwnMaterialsServiceModel{" +
                "metalOwn=" + metalOwn +
                ", gasOwn=" + gasOwn +
                ", diamondOwn=" + diamondOwn +
                ", energyOwn=" + energyOwn +
                '}';
    }
}

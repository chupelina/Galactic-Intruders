package com.example.demo.models.viewModels;

public class StationViewModel {
    private long id;
    private String name;
    private Integer metal;
    private Integer gas;
    private Integer diamond;
    private Integer energy;
    private int time;
    private String description;
    private int currentLevel;

    public long getId() {
        return id;
    }

    public StationViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StationViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMetal() {
        return metal;
    }

    public StationViewModel setMetal(Integer metal) {
        this.metal = metal;
        return this;
    }

    public Integer getGas() {
        return gas;
    }

    public StationViewModel setGas(Integer gas) {
        this.gas = gas;
        return this;
    }

    public Integer getDiamond() {
        return diamond;
    }

    public StationViewModel setDiamond(Integer diamond) {
        this.diamond = diamond;
        return this;
    }

    public Integer getEnergy() {
        return energy;
    }

    public StationViewModel setEnergy(Integer energy) {
        this.energy = energy;
        return this;
    }

    public int getTime() {
        return time;
    }

    public StationViewModel setTime(int time) {
        this.time = time;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StationViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public StationViewModel setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        return this;
    }
}

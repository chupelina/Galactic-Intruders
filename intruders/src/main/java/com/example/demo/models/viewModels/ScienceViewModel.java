package com.example.demo.models.viewModels;

import javax.persistence.Column;
import java.time.LocalTime;

public class ScienceViewModel {
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

    public ScienceViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public ScienceViewModel setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        return this;
    }

    public String getName() {
        return name;
    }

    public ScienceViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMetal() {
        return metal;
    }

    public ScienceViewModel setMetal(Integer metal) {
        this.metal = metal;
        return this;
    }

    public Integer getGas() {
        return gas;
    }

    public ScienceViewModel setGas(Integer gas) {
        this.gas = gas;
        return this;
    }

    public Integer getDiamond() {
        return diamond;
    }

    public ScienceViewModel setDiamond(Integer diamond) {
        this.diamond = diamond;
        return this;
    }

    public Integer getEnergy() {
        return energy;
    }

    public ScienceViewModel setEnergy(Integer energy) {
        this.energy = energy;
        return this;
    }

    public int getTime() {
        return time;
    }

    public ScienceViewModel setTime(int time) {
        this.time = time;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScienceViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}

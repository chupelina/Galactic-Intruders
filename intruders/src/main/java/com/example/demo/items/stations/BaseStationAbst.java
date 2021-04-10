package com.example.demo.items.stations;

import com.example.demo.models.entities.StationEntity;

public class BaseStationAbst extends StationEntity implements BaseStation {

    private String name;
    private Integer metal;
    private Integer gas;
    private Integer diamond;
    private Integer energy;
    private int time;
    private String description;

    public BaseStationAbst() {
    }

    public BaseStationAbst(String name, Integer metal, Integer gas, Integer diamond, Integer energy, int time, String description) {
        this.name = name;
        this.metal = metal;
        this.gas = gas;
        this.diamond = diamond;
        this.energy = energy;
        this.time = time;
        this.description = description;
    }


    @Override
    public int increaseIncomes(int type) {
        return (int)Math.ceil(type*1.05);
    }



    @Override
    public int increaseCapacity(int type) {
        return (int)Math.ceil(type*1.1);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public BaseStationAbst setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getMetal() {
        return metal;
    }

    @Override
    public BaseStationAbst setMetal(Integer metal) {
        this.metal = metal;
        return this;
    }

    @Override
    public Integer getGas() {
        return gas;
    }

    @Override
    public BaseStationAbst setGas(Integer gas) {
        this.gas = gas;
        return this;
    }

    @Override
    public Integer getDiamond() {
        return diamond;
    }

    @Override
    public BaseStationAbst setDiamond(Integer diamond) {
        this.diamond = diamond;
        return this;
    }

    @Override
    public Integer getEnergy() {
        return energy;
    }

    @Override
    public BaseStationAbst setEnergy(Integer energy) {
        this.energy = energy;
        return this;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public BaseStationAbst setTime(int time) {
        this.time = time;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public BaseStationAbst setDescription(String description) {
        this.description = description;
        return this;
    }
}

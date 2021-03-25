package com.example.demo.models.viewModels;

import javax.persistence.Column;
import java.time.LocalTime;

public class ShipViewModel {
    private long id;
    private String name;
    private String imgUrl;
    private Integer metal;
    private Integer gas;
    private Integer diamond;
    private Integer energy;
    private String time;
    private String description;
    private int count;

    public long getId() {
        return id;
    }

    public ShipViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ShipViewModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Integer getMetal() {
        return metal;
    }

    public ShipViewModel setMetal(Integer metal) {
        this.metal = metal;
        return this;
    }

    public Integer getGas() {
        return gas;
    }

    public ShipViewModel setGas(Integer gas) {
        this.gas = gas;
        return this;
    }

    public Integer getDiamond() {
        return diamond;
    }

    public ShipViewModel setDiamond(Integer diamond) {
        this.diamond = diamond;
        return this;
    }

    public Integer getEnergy() {
        return energy;
    }

    public ShipViewModel setEnergy(Integer energy) {
        this.energy = energy;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ShipViewModel setTime(String time) {
        this.time = time;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShipViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCount() {
        return count;
    }

    public ShipViewModel setCount(int count) {
        this.count = count;
        return this;
    }
}

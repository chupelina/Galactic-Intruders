package com.example.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "stations")
public class StationEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private Integer metal;
    private Integer gas;
    private Integer diamond;
    private Integer energy;
    @Column(nullable = false)
    private int time;
    @Column(nullable = false)
    private String description;

    public StationEntity() {
    }

    public StationEntity(String name, Integer metal, Integer gas, Integer diamond, Integer energy, int time, String description) {
        this.name = name;
        this.metal = metal;
        this.gas = gas;
        this.diamond = diamond;
        this.energy = energy;
        this.time = time;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public StationEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMetal() {
        return metal;
    }

    public StationEntity setMetal(Integer metal) {
        this.metal = metal;
        return this;
    }

    public Integer getGas() {
        return gas;
    }

    public StationEntity setGas(Integer gas) {
        this.gas = gas;
        return this;
    }

    public Integer getDiamond() {
        return diamond;
    }

    public StationEntity setDiamond(Integer diamond) {
        this.diamond = diamond;
        return this;
    }

    public Integer getEnergy() {
        return energy;
    }

    public StationEntity setEnergy(Integer energy) {
        this.energy = energy;
        return this;
    }

    public int getTime() {
        return time;
    }

    public StationEntity setTime(int time) {
        this.time = time;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StationEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

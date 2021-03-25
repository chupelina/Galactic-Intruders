package com.example.demo.models.entities;

import com.zaxxer.hikari.util.ClockSource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name="ships")
public class ShipEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imgUrl;
    private Integer metal;
    private Integer gas;
    private Integer diamond;
    private Integer energy;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String description;

    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ShipEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Integer getMetal() {
        return metal;
    }

    public ShipEntity setMetal(Integer metal) {
        this.metal = metal;
        return this;
    }

    public Integer getGas() {
        return gas;
    }

    public ShipEntity setGas(Integer gas) {
        this.gas = gas;
        return this;
    }

    public Integer getDiamond() {
        return diamond;
    }

    public ShipEntity setDiamond(Integer diamond) {
        this.diamond = diamond;
        return this;
    }

    public Integer getEnergy() {
        return energy;
    }

    public ShipEntity setEnergy(Integer energy) {
        this.energy = energy;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ShipEntity setTime(String time) {
        this.time = time;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShipEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "ShipEntity{" +
                "name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", metal=" + metal +
                ", gas=" + gas +
                ", diamond=" + diamond +
                ", energy=" + energy +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

package com.example.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planet_stations")
public class PlanetStationEntity extends BaseEntity {
    @ManyToOne
    private PlanetEntity planetEntity;
    @ManyToOne
    private StationEntity stationEntity;
    @Column(nullable = false)
    private Integer level;

    public PlanetEntity getPlanetEntity() {
        return planetEntity;
    }

    public PlanetStationEntity setPlanetEntity(PlanetEntity planetEntity) {
        this.planetEntity = planetEntity;
        return this;
    }

    public StationEntity getStationEntity() {
        return stationEntity;
    }

    public PlanetStationEntity setStationEntity(StationEntity stationEntity) {
        this.stationEntity = stationEntity;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public PlanetStationEntity setLevel(Integer level) {
        this.level = level;
        return this;
    }
}

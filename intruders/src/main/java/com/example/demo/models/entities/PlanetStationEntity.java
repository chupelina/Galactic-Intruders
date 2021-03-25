package com.example.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planet_stations")
public class PlanetStationEntity extends BaseEntity {
    @ManyToOne
    private PlanetResourceEntity planetResourceEntity;
    @ManyToOne
    private StationEntity stationEntity;
    @Column(nullable = false)
    private Integer level;

    public PlanetResourceEntity getPlanetResourceEntity() {
        return planetResourceEntity;
    }

    public PlanetStationEntity setPlanetResourceEntity(PlanetResourceEntity planetResourceEntity) {
        this.planetResourceEntity = planetResourceEntity;
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

package com.example.demo.models.entities;

import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planet_sciences")
public class PlanetScienceEntity extends BaseEntity {
    @ManyToOne
    private PlanetEntity planetEntity;
    @ManyToOne
    private ScienceEntity scienceEntity;
    @Column(nullable = false)
    private Integer level;

    public PlanetEntity getPlanetEntity() {
        return planetEntity;
    }

    public PlanetScienceEntity setPlanetEntity(PlanetEntity planetEntity) {
        this.planetEntity = planetEntity;
        return this;
    }

    public ScienceEntity getScienceEntity() {
        return scienceEntity;
    }

    public PlanetScienceEntity setScienceEntity(ScienceEntity scienceEntity) {
        this.scienceEntity = scienceEntity;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public PlanetScienceEntity setLevel(Integer level) {
        this.level = level;
        return this;
    }
}

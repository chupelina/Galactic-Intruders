package com.example.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="planet_ships")
public class PlanetShipEntity extends BaseEntity {
    @ManyToOne
    private PlanetResourceEntity planetResourceEntity;
    @ManyToOne
    private ShipEntity shipEntity;
    @Column(nullable = false)
    private Integer countShips;

    public PlanetResourceEntity getPlanetResourceEntity() {
        return planetResourceEntity;
    }

    public PlanetShipEntity setPlanetResourceEntity(PlanetResourceEntity planetResourceEntity) {
        this.planetResourceEntity = planetResourceEntity;
        return this;
    }

    public ShipEntity getShipEntity() {
        return shipEntity;
    }

    public PlanetShipEntity setShipEntity(ShipEntity shipEntity) {
        this.shipEntity = shipEntity;
        return this;
    }

    public Integer getCountShips() {
        return countShips;
    }

    public PlanetShipEntity setCountShips(Integer countShips) {
        this.countShips = countShips;
        return this;
    }
}

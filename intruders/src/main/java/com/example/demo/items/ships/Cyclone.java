package com.example.demo.items.ships;

import javax.persistence.Entity;

@Entity
public class Cyclone extends BaseShip {
    public Cyclone() {
        super("Cyclone", "../img/ships/cyclone.jpg", 22, 13, 33, 10, 18,
                "Protect your planet from invasion", 200, 60, false);
    }
}

package com.example.demo.items.ships;

import javax.persistence.Entity;

@Entity
public class Scout extends BaseShip {

    public Scout() {
        super("Scout", "../img/ships/scout.jpg", 16, 15, 22, 20, 65,
                "Observe the enemies without being noticed", 200, 6, false);
    }
}

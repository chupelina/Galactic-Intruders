package com.example.demo.items.ships;

import javax.persistence.Entity;

@Entity
public class Destroyer extends BaseShip {

    public Destroyer() {
        super("Destroyer", "../img/ships/destroyer.jpg", 22, 18, 50, 100, 60,
                "Helps you to attack enemies", 150, 60);
    }
}

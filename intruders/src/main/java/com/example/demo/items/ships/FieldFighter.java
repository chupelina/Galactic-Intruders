package com.example.demo.items.ships;

import javax.persistence.Entity;

@Entity
public class FieldFighter extends BaseShip {

    public FieldFighter() {
        super("Field Fighter", "../img/ships/fieldFighter.jpg", 40, 3, 17, 3, 33,
                "Help you to protect the destroyers", 300,40, false);
    }
}

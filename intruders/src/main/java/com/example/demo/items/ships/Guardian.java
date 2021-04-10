package com.example.demo.items.ships;

import javax.persistence.Entity;

@Entity
public class Guardian extends BaseShip {

    public Guardian() {
        super("Guardian", "../img/ships/guardian.jpg", 20, 11, 3, 19, 20,
                "Protect your planet from invasion (quick and powerful)", 200, 30);
    }
}

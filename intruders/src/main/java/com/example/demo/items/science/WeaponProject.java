package com.example.demo.items.science;

import com.example.demo.models.entities.ScienceEntity;
import com.example.demo.models.entities.ShipEntity;

import javax.persistence.Entity;


@Entity
public class WeaponProject extends ScienceEntity {

    public WeaponProject() {
       super("Weapon project", 10, 13, 12, 3, 12, "Increase your ship's weapons");
    }


}

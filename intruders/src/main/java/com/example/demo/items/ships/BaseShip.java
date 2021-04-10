package com.example.demo.items.ships;

import com.example.demo.models.entities.ShipEntity;
import com.example.demo.models.entities.enums.MaterialEnum;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseShip extends ShipEntity {
    private Integer health;
    private Integer attack;

    public BaseShip(String name, String imgUrl, Integer metal, Integer gas, Integer diamond, Integer energy, Integer time, String description, int health, int attack) {
        super(name, imgUrl, metal, gas, diamond, energy, time, description);
        this.attack=attack;
        this.health=health;
    }

    public Map<MaterialEnum, Integer> increaseIncomes(int count,int metal, int gas, int diamond, int energy) {
        return new HashMap<>();
    }

    public Integer getHealth() {
        return health;
    }

    public BaseShip setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getAttack() {
        return attack;
    }

    public BaseShip setAttack(Integer attack) {
        this.attack = attack;
        return this;
    }
}

package com.example.demo.models.serviceModels;

public class ShipModel {
    private int count;
    private String name;
    private int attack;
    private int health;
    private int goneToBattle;
    private boolean isDead;

    public boolean isDead() {
        return isDead;
    }

    public ShipModel setDead(boolean dead) {
        isDead = dead;
        return this;
    }

    public int getGoneToBattle() {
        return goneToBattle;
    }

    public ShipModel setGoneToBattle(int goneToBattle) {
        this.goneToBattle = goneToBattle;
        return this;
    }

    public int getCount() {
        return count;
    }

    public ShipModel setCount(int count) {
        this.count = count;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public ShipModel setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public int getHealth() {
        return health;
    }

    public ShipModel setHealth(int health) {
        this.health = health;
        return this;
    }
}

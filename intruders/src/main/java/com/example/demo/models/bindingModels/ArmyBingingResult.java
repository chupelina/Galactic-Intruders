package com.example.demo.models.bindingModels;

import com.example.demo.models.serviceModels.ShipModel;

import java.util.ArrayList;
import java.util.List;

public class ArmyBingingResult {
    private List<ShipModel> ships;

    public ArmyBingingResult() {
        this.ships = new ArrayList<>();
    }

    public List<ShipModel> getShips() {
        return ships;
    }

    public ArmyBingingResult setShips(List<ShipModel> ships) {
        this.ships = ships;
        return this;
    }
}

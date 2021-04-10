package com.example.demo.items.ships;

import com.example.demo.models.entities.enums.MaterialEnum;

import javax.persistence.Entity;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class CargoShip extends BaseShip {

    public CargoShip() {
        super("CargoShip", "../img/ships/cargoship.jpg", 15, 1, 13, 20, 13,
                "Increase your incomes with 5% for every one", 100, 1);
    }

    @Override
    public Map<MaterialEnum, Integer> increaseIncomes(int count,int metal, int gas, int diamond, int energy) {
        Map<MaterialEnum , Integer> incomes = new LinkedHashMap<>();
        incomes.put(MaterialEnum.DIAMOND, (int)Math.ceil(diamond*(1+0.05*count)));
        incomes.put(MaterialEnum.GAS, (int)Math.ceil(gas*(1+0.05*count)));
        incomes.put(MaterialEnum.METAL, (int)Math.ceil(metal*(1+0.05*count)));
        incomes.put(MaterialEnum.ENERGY, (int)Math.ceil(energy*(1+0.05*count)));
        return incomes;
    }
}

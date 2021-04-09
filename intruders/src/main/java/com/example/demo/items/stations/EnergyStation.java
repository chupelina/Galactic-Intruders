package com.example.demo.items.stations;

import com.example.demo.items.BaseStation;
import com.example.demo.models.entities.StationEntity;

import javax.persistence.Entity;

@Entity
public class EnergyStation extends BaseStationAbst {


    public EnergyStation() {
        super("Energy Station", 14, 10, 33, 0, 23
                , "Increase your energy incomes with 5 % and your capacity with 10%");
    }



    @Override
    public int increaseEnergyIncomes(int energy) {
        return (int)Math.ceil(energy*1.05);
    }


    @Override
    public int increaseEnergyCapacity(int energy) {
        return (int)Math.ceil(energy*1.1);
    }
}

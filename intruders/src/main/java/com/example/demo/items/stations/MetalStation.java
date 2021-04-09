package com.example.demo.items.stations;

import com.example.demo.items.BaseStation;
import com.example.demo.models.entities.StationEntity;

import javax.persistence.Entity;

@Entity
public class MetalStation extends BaseStationAbst {


    public MetalStation() {
        super("Metal Station", 40, 10, 20, 30, 12
                , "Increase your metal incomes with 5 % and your capacity with 10%");
    }


    @Override
    public int increaseMetalIncomes(int metal) {
        return (int)Math.ceil(metal*1.05);
    }


    @Override
    public int increaseMetalCapacity(int metal) {
        return (int)Math.ceil(metal*1.1);
    }

}

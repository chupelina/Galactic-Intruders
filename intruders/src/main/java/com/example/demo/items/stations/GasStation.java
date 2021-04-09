package com.example.demo.items.stations;

import com.example.demo.items.BaseStation;
import com.example.demo.models.entities.StationEntity;

import javax.persistence.Entity;

@Entity
public class GasStation extends BaseStationAbst {


    public GasStation() {
        super("Gas Station", 35, 15, 17, 5, 20
                , "Increase your gas incomes with 5 % and your capacity with 10%");
    }



    @Override
    public int increaseGasIncomes(int gas) {
        return (int)Math.ceil(gas*1.05);
    }


    @Override
    public int increaseGasCapacity(int gas) {
        return (int)Math.ceil(gas*1.1);
    }

}

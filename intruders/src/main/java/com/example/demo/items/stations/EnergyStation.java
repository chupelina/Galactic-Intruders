package com.example.demo.items.stations;

import javax.persistence.Entity;

@Entity
public class EnergyStation extends BaseStationAbst {


    public EnergyStation() {
        super("Energy Station", 14, 10, 33, 0, 23
                , "Increase your energy incomes with 5 % and your capacity with 10%");
    }

}

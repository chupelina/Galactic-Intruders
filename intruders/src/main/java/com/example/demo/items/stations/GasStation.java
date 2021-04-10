package com.example.demo.items.stations;

import javax.persistence.Entity;

@Entity
public class GasStation extends BaseStationAbst {


    public GasStation() {
        super("Gas Station", 35, 15, 17, 5, 20
                , "Increase your gas incomes with 5 % and your capacity with 10%");
    }



}

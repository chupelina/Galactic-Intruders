package com.example.demo.items.stations;

import javax.persistence.Entity;

@Entity
public class DiamondStation extends BaseStationAbst {


    public DiamondStation() {
        super("Diamond Station", 17, 20, 35, 17, 19
                , "Increase your diamond incomes with 5 % and your capacity with 10%");
    }


}

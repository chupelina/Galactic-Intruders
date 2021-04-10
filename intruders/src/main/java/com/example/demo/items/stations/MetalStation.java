package com.example.demo.items.stations;

import javax.persistence.Entity;

@Entity
public class MetalStation extends BaseStationAbst {


    public MetalStation() {
        super("Metal Station", 40, 10, 20, 30, 12
                , "Increase your metal incomes with 5 % and your capacity with 10%");
    }


}

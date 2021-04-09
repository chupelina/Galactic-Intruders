package com.example.demo.items.science;

import com.example.demo.models.entities.ScienceEntity;

import javax.persistence.Entity;

@Entity
public class EnergyProject extends ScienceEntity {

    public EnergyProject(){
        super("Energy project", 13,16,2,11, 17,"Increase your energy incomes with 5%");
    }
}

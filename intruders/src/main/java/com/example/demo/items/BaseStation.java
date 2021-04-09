package com.example.demo.items;

import com.example.demo.models.entities.BaseEntity;

public interface BaseStation {

    int increaseMetalIncomes(int metal);

    int increaseGasIncomes(int gas);

    int increaseDiamondIncomes(int diamond);

    int increaseEnergyIncomes(int energy);

    int increaseMetalCapacity(int metal);

    int increaseGasCapacity(int gas);

    int increaseDiamondCapacity(int diamond);

    int increaseEnergyCapacity(int energy);

}

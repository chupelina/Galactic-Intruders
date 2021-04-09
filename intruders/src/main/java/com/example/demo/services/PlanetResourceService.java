package com.example.demo.services;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.enums.MaterialEnum;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;

public interface PlanetResourceService {

    boolean changeMaterials(BankBindingModel bankBindingModel, PlanetResourceModelInfo name);

    void decreaseOwns(PlanetResourceEntity planet , int diamond, int energy, int metal, int gas);

    void increaseOwns(int i);

    PlanetResourceModelInfo findById(Long planetEntityId);

    void stopScheduleForPlanet();

    void increaseIncomesAndCapacity(PlanetResourceEntity planetResourceEntity, int increaseCapacity, int increaseIncomes, MaterialEnum material);
}

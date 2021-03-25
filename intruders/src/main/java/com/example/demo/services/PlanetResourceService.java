package com.example.demo.services;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.serviceModels.PlanetResourceServiceModel;
import com.example.demo.models.serviceModels.PlanetServiceModel;

public interface PlanetResourceService {

    boolean changeMaterials(BankBindingModel bankBindingModel, PlanetServiceModel name);

    void decreaseOwns(PlanetResourceEntity planet ,int diamond, int energy, int metal, int gas);


    PlanetResourceEntity findById(Long planetEntityId);
}

package com.example.demo.services;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetResourceEntity;

public interface PlanetResourceService {
    PlanetResourceEntity createPlanetResourceEntity();

    boolean changeMaterials(BankBindingModel bankBindingModel, PlanetResourceEntity planetResourceEntity);

    void decreaseOwns(PlanetResourceEntity planet ,int diamond, int energy, int metal, int gas);
}

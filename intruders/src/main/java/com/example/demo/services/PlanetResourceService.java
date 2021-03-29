package com.example.demo.services;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.PlanetServiceModel;

public interface PlanetResourceService {

    boolean changeMaterials(BankBindingModel bankBindingModel, PlanetResourceModelInfo name);

    void decreaseOwns(PlanetResourceEntity planet ,int diamond, int energy, int metal, int gas);


    PlanetResourceModelInfo findById(Long planetEntityId);
}

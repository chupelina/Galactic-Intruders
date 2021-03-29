package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetScienceEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.ScienceViewModel;

import java.util.List;

public interface ScienceService {
    void seed();

    List<ScienceViewModel> getAllScienceProjectsByCurrentPlanet(PlanetResourceModelInfo planetId);

    void updateScienceLevel(Long id);

    boolean createNewScience(AddingBindingModel addingBindingModel);

     PlanetScienceEntity getPlanetScienceEntityById(long id);
}

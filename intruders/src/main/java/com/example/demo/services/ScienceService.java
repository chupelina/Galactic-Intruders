package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetScienceEntity;
import com.example.demo.models.viewModels.ScienceViewModel;

import java.util.List;
import java.util.Set;

public interface ScienceService {
    void seed();

    List<ScienceViewModel> getAllScienceProjectsByCurrentPlanet(PlanetEntity planetEntity);

    void createScience(PlanetEntity planet);

    void updateScienceLevel(Long id);

    boolean createOne(AddingBindingModel addingBindingModel);
}

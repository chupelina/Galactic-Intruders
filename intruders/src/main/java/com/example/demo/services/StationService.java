package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.viewModels.StationViewModel;

import java.util.List;

public interface StationService {
    void seed();

    List<StationViewModel> getAllStationsByCurrentPlanet(PlanetEntity planetEntity);

    void updateScienceLevel(Long id);

    void createStations(PlanetEntity planet);

    boolean createOne(AddingBindingModel addingBindingModel);
}

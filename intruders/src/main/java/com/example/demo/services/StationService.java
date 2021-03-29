package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetStationEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.StationViewModel;

import java.util.List;

public interface StationService {
    void seed();

    List<StationViewModel> getAllStationsByCurrentPlanet(PlanetResourceModelInfo planetEntity);

    void updateScienceLevel(Long id);

    boolean createNewStation(AddingBindingModel addingBindingModel);

    PlanetStationEntity findByPlanetStationEntityId(long id);
}

package com.example.demo.services;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.serviceModels.PlanetInfoServiceModel;

public interface PlanetService {
    PlanetEntity createPlanet();


    PlanetInfoServiceModel getCurrentPlanet(PlanetEntity planet);

    PlanetEntity findPlanetById(long planetId);
}

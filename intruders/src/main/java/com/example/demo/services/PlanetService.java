package com.example.demo.services;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.serviceModels.PlanetServiceModel;

public interface PlanetService {
    PlanetServiceModel getCurrentPlanet(String username);

    PlanetEntity findPlanetById(long planetId);
}

package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.viewModels.ShipViewModel;

import java.util.List;

public interface ShipService {
    void seedShips();

    void createArmy(PlanetEntity planet);

    List<ShipViewModel> getAllScienceProjectsByCurrentPlanet(PlanetEntity planet);

    void addShips(Long shipId, int count);

    boolean createShip(AddingBindingModel addingBindingModel);
}

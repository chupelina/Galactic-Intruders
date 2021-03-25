package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.models.viewModels.ShipViewModel;

import java.util.List;

public interface ShipService {
    void seedShips();

    List<ShipViewModel> getAllScienceProjectsByCurrentPlanet(Long id);

    void addShips(Long shipId, int count);

    boolean createNewShip(AddingBindingModel addingBindingModel);
}

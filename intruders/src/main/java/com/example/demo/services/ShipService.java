package com.example.demo.services;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetShipEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.ShipModel;
import com.example.demo.models.viewModels.ShipViewModel;

import java.util.List;

public interface ShipService {
    void seedShips();

    List<ShipViewModel> getAllShipsByCurrentPlanet(PlanetResourceModelInfo planetResourceModelInfo);

    void addShips(Long shipId, int count);

    boolean createNewShip(AddingBindingModel addingBindingModel);

    PlanetShipEntity returnPlanetShipEntityById(long id);

    List<ShipModel> getArmy(PlanetResourceModelInfo planetModelInfo);

    List<ShipModel> createEnemy();

    void decreaseArmy(String ships, PlanetResourceModelInfo planetModelInfo);
}

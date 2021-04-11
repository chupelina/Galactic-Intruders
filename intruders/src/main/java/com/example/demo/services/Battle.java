package com.example.demo.services;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.ShipModel;

import java.util.List;

public interface Battle {
    boolean startBattle(String data, List<ShipModel> army, PlanetResourceModelInfo planetModelInfo);
}

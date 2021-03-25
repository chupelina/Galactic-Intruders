package com.example.demo.services.implementation;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import com.example.demo.models.serviceModels.PlanetInfoServiceModel;
import com.example.demo.repositories.PlanetRepository;
import com.example.demo.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepository planetRepository;
    private final ModelMapper modelMapper;
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;
    private final PlanetResourceService planetResourceService;
    private final ScienceService scienceService;
    private final ShipService shipService;
    private final StationService stationService;

    public PlanetServiceImpl(PlanetRepository planetRepository, ModelMapper modelMapper
            , OwnMaterialsServiceModel ownMaterialsServiceModel, PlanetResourceService planetResourceService, ScienceService scienceService, ShipService shipService, StationService stationService) {
        this.planetRepository = planetRepository;
        this.modelMapper = modelMapper;
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
        this.planetResourceService = planetResourceService;
        this.scienceService = scienceService;
        this.shipService = shipService;
        this.stationService = stationService;
    }

    public PlanetEntity createPlanet() {
        PlanetEntity planet = new PlanetEntity();
        planet.setDescription(getDescription())
                .setImgUrl(getImageUrl())
                .setName(getName())
                .setPlanetResourceEntity(planetResourceService.createPlanetResourceEntity());
        planetRepository.save(planet);
        scienceService.createScience(planet);
        shipService.createArmy(planet);
        stationService.createStations(planet);
        return planet;
    }

    @Override
    public PlanetInfoServiceModel getCurrentPlanet(PlanetEntity planetEntity) {

        PlanetResourceEntity planetResourceEntity = planetEntity.getPlanetResourceEntity();
        PlanetInfoServiceModel planetInfoServiceModel = modelMapper.map(planetResourceEntity, PlanetInfoServiceModel.class);
        planetInfoServiceModel
                .setName(planetEntity.getName()).setDescription(planetEntity.getDescription())
                .setId(planetEntity.getId());
        ownMaterialsServiceModel.setDiamondOwn(planetResourceEntity.getDiamondOwn())
                .setEnergyOwn(planetResourceEntity.getEnergyOwn())
                .setGasOwn(planetResourceEntity.getGasOwn())
                .setMetalOwn(planetResourceEntity.getMetalOwn()).setImage(planetEntity.getImgUrl())
        .setPlanetId(planetEntity.getId());
        return planetInfoServiceModel;
    }

    @Override
    public PlanetEntity findPlanetById(long planetId) {
        // TODO: 20.3.2021 error handling
        return planetRepository.findById(planetId).orElse(null);
    }

    private String getName() {
        // TODO: 19.3.2021 fill with data
        List<String> names = List.of("dodo", "coco");
        Random random = new Random();
        int current = random.nextInt(names.size());
        return names.get(current);
    }

    private String getImageUrl() {
        List<String> images = List.of("/img/planets/planet-1.jpg", "/img/planets/planet-2.jpg", "/img/planets/planet-3.png",
                "/img/planets/planet-4.jpg", "/img/planets/planet-5.jpg", "/img/planets/planet-6.jpg", "/img/planets/planet-7.jpg"
                , "/img/planets/planet-8.jpg", "/img/planets/planet-9.jpg", "/img/planets/planet-10.jpg");
        Random random = new Random();
        int current = random.nextInt(images.size());
        return images.get(current);
    }

    private String getDescription() {
        // TODO: 19.3.2021 fill with data
        List<String> descriptions = List.of("Something special....", "Something different....");
        Random random = new Random();
        int current = random.nextInt(descriptions.size());
        return descriptions.get(current);
    }



}

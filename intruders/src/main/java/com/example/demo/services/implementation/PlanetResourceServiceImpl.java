package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.repositories.PlanetResourceRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.PlanetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class PlanetResourceServiceImpl implements PlanetResourceService {
    private final PlanetResourceRepository planetResourceRepository;
    private final PlanetModelInfo planetModelInfo;
    private final PlanetService planetService;

    public PlanetResourceServiceImpl(PlanetResourceRepository planetResourceRepository,
                                     PlanetModelInfo planetModelInfo, PlanetService planetService) {
        this.planetResourceRepository = planetResourceRepository;
        this.planetModelInfo = planetModelInfo;
        this.planetService = planetService;
    }


    private PlanetResourceEntity createPlanetResourceEntity(PlanetEntity planetEntity) {
        PlanetResourceEntity current = seedCurrentResources(new PlanetResourceEntity());
        current.setPlanetEntity(planetEntity);
        planetResourceRepository.save(current);
        return current;

    }

    @Override
    public PlanetResourceEntity findById(Long planetEntityId) {
        Optional<PlanetResourceEntity> planetResource = planetResourceRepository.findById(planetEntityId);
        PlanetResourceEntity  planetResourceEntity;
        if(planetResource.isEmpty()){
           planetResourceEntity = createPlanetResourceEntity(planetService.findPlanetById(planetEntityId));
        }else{
          planetResourceEntity  = planetResource.get();
        }

        planetModelInfo.setDiamondCapacity(planetResourceEntity.getDiamondCapacity()).setDiamondForMin(planetResourceEntity.getDiamondForMin()).setDiamondOwn(planetResourceEntity.getDiamondOwn())
                .setEnergyCapacity(planetResourceEntity.getEnergyCapacity()).setEnergyForMin(planetResourceEntity.getEnergyForMin()).setEnergyOwn(planetResourceEntity.getEnergyOwn())
                .setMetalCapacity(planetResourceEntity.getMetalCapacity()).setMetalForMin(planetResourceEntity.getMetalForMin()).setMetalOwn(planetResourceEntity.getMetalOwn())
                .setGasCapacity(planetResourceEntity.getGasCapacity()).setGasForMin(planetResourceEntity.getGasForMin()).setGasOwn(planetResourceEntity.getGasOwn()).setId(planetResourceEntity.getId());

        return planetResourceEntity;
    }


    @Override
    public boolean changeMaterials(BankBindingModel bankBindingModel, PlanetServiceModel currentPlanet) {
        PlanetResourceEntity planet = planetResourceRepository.findFirstByPlanetEntity_id(currentPlanet.getId()).orElseThrow();
        try {
            switch (bankBindingModel.getType()) {
                case METAL:
                    planet.setMetalOwn(decreaseMaterials(planet.getMetalOwn(), bankBindingModel.getCount()));
                    break;
                case ENERGY:
                    planet.setEnergyOwn(decreaseMaterials(planet.getEnergyOwn(), bankBindingModel.getCount()));
                    break;
                case GAS:
                    planet.setGasOwn(decreaseMaterials(planet.getGasOwn(), bankBindingModel.getCount()));
                    break;
                case DIAMOND:
                    planet.setDiamondOwn(decreaseMaterials(planet.getDiamondOwn(), bankBindingModel.getCount()));
                    break;
            }
            switch (bankBindingModel.getWanted()) {
                case METAL:
                    planet.setMetalOwn(increaseMaterials(planet.getMetalOwn(), bankBindingModel.getCount(), planet.getMetalCapacity()));
                    break;
                case ENERGY:
                    planet.setEnergyOwn(increaseMaterials(planet.getEnergyOwn(), bankBindingModel.getCount(), planet.getEnergyCapacity()));
                    break;
                case GAS:
                    planet.setGasOwn(increaseMaterials(planet.getGasOwn(), bankBindingModel.getCount(), planet.getGasCapacity()));
                    break;
                case DIAMOND:
                    planet.setDiamondOwn(increaseMaterials(planet.getDiamondOwn(), bankBindingModel.getCount(), planet.getDiamondCapacity()));
                    break;
            }
            planetResourceRepository.save(planet);
            planetModelInfo.setDiamondCapacity(planet.getDiamondCapacity()).setDiamondForMin(planet.getDiamondForMin()).setDiamondOwn(planet.getDiamondOwn())
                    .setEnergyCapacity(planet.getEnergyCapacity()).setEnergyForMin(planet.getEnergyForMin()).setEnergyOwn(planet.getEnergyOwn())
                    .setMetalCapacity(planet.getMetalCapacity()).setMetalForMin(planet.getMetalForMin()).setMetalOwn(planet.getMetalOwn())
                    .setGasCapacity(planet.getGasCapacity()).setGasForMin(planet.getGasForMin()).setGasOwn(planet.getGasOwn());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }

    }

    @Override
    public void decreaseOwns(PlanetResourceEntity planet, int diamond, int energy, int metal, int gas) {
        planet.setMetalOwn(decreaseMaterials(planet.getMetalOwn(), metal));
        planet.setDiamondOwn(decreaseMaterials(planet.getDiamondOwn() , diamond));
        planet.setGasOwn(decreaseMaterials(planet.getGasOwn() , gas));
        planet.setEnergyOwn(decreaseMaterials(planet.getEnergyOwn() , energy));
        planetResourceRepository.save(planet);
        planetModelInfo.setDiamondCapacity(planet.getDiamondCapacity()).setDiamondForMin(planet.getDiamondForMin()).setDiamondOwn(planet.getDiamondOwn())
                .setEnergyCapacity(planet.getEnergyCapacity()).setEnergyForMin(planet.getEnergyForMin()).setEnergyOwn(planet.getEnergyOwn())
                .setMetalCapacity(planet.getMetalCapacity()).setMetalForMin(planet.getMetalForMin()).setMetalOwn(planet.getMetalOwn())
                .setGasCapacity(planet.getGasCapacity()).setGasForMin(planet.getGasForMin()).setGasOwn(planet.getGasOwn());
    }


    private static int decreaseMaterials(int first, int second) {
        if (first - second < 0) {
            throw new IllegalArgumentException("Not enough materials");
        }
        return first - second;
    }

    private static int increaseMaterials(int first, int second, int capacity) {
        if ((int) (first + Math.round(second * 0.8)) > capacity) {
            return capacity;
        } else {
            return (int) (first + Math.round(second * 0.8));
        }
    }


    private PlanetResourceEntity seedCurrentResources(PlanetResourceEntity planet) {
        planet.setDiamondForMin(randomResourceMinNumber())
                .setDiamondOwn(randomResourceOwnNumber())
                .setEnergyForMin(randomResourceMinNumber())
                .setEnergyOwn(randomResourceOwnNumber())
                .setGasForMin(randomResourceMinNumber())
                .setGasOwn(randomResourceOwnNumber())
                .setMetalForMin(randomResourceMinNumber())
                .setMetalOwn(randomResourceOwnNumber())
                .setMetalCapacity(1000).setEnergyCapacity(1000).setDiamondCapacity(1000).setGasCapacity(1000);

        return planet;
    }

    private int randomResourceOwnNumber() {
        Random random = new Random();
        int current = random.nextInt(500) + 500;
        return current;
    }

    private int randomResourceMinNumber() {
        Random random = new Random();
        int current = random.nextInt(5) + 10;
        return current;
    }
}

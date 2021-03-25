package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import com.example.demo.repositories.PlanetResourceRepository;
import com.example.demo.services.PlanetResourceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PlanetResourceServiceImpl implements PlanetResourceService {
    private final PlanetResourceRepository planetResourceRepository;
    private final ModelMapper modelMapper;
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;

    public PlanetResourceServiceImpl(PlanetResourceRepository planetResourceRepository, OwnMaterialsServiceModel ownMaterialsServiceModel, ModelMapper modelMapper, OwnMaterialsServiceModel ownMaterialsServiceModel1) {
        this.planetResourceRepository = planetResourceRepository;
        this.modelMapper = modelMapper;
        this.ownMaterialsServiceModel = ownMaterialsServiceModel1;
    }


    public PlanetResourceEntity createPlanetResourceEntity() {
        PlanetResourceEntity current = seedCurrentResources(new PlanetResourceEntity());
        planetResourceRepository.save(current);
        return current;
    }

    @Override
    public boolean changeMaterials(BankBindingModel bankBindingModel, PlanetResourceEntity planet) {
        try {
            switch (bankBindingModel.getType()) {
                case "metal":
                    planet.setMetalOwn(decreaseMaterials(planet.getMetalOwn(), bankBindingModel.getCount()));
                    break;
                case "energy":
                    planet.setEnergyOwn(decreaseMaterials(planet.getEnergyOwn(), bankBindingModel.getCount()));
                    break;
                case "gas":
                    planet.setGasOwn(decreaseMaterials(planet.getGasOwn(), bankBindingModel.getCount()));
                    break;
                case "diamond":
                    planet.setDiamondOwn(decreaseMaterials(planet.getDiamondOwn(), bankBindingModel.getCount()));
                    break;
            }
            switch (bankBindingModel.getWanted()) {
                case "metal":
                    planet.setMetalOwn(increaseMaterials(planet.getMetalOwn(), bankBindingModel.getCount(), planet.getMetalCapacity()));
                    break;
                case "energy":
                    planet.setEnergyOwn(increaseMaterials(planet.getEnergyOwn(), bankBindingModel.getCount(), planet.getEnergyCapacity()));
                    break;
                case "gas":
                    planet.setGasOwn(increaseMaterials(planet.getGasOwn(), bankBindingModel.getCount(), planet.getGasCapacity()));
                    break;
                case "diamond":
                    planet.setDiamondOwn(increaseMaterials(planet.getDiamondOwn(), bankBindingModel.getCount(), planet.getDiamondCapacity()));
                    break;
            }
            modelMapper.map(planetResourceRepository, OwnMaterialsServiceModel.class);
            planetResourceRepository.save(planet);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }

    }

    @Override
    public void decreaseOwns(PlanetResourceEntity planet, int diamond, int energy, int metal, int gas) {
        planet.setMetalOwn(planet.getMetalOwn()-metal);
        planet.setDiamondOwn(planet.getDiamondOwn()-diamond);
        planet.setGasOwn(planet.getGasOwn()-gas);
        planet.setEnergyOwn(planet.getEnergyOwn()-energy);
        planetResourceRepository.save(planet);
        ownMaterialsServiceModel.setMetalOwn(planet.getMetalOwn());
        ownMaterialsServiceModel.setGasOwn(planet.getGasOwn());
        ownMaterialsServiceModel.setEnergyOwn(planet.getEnergyOwn());
        ownMaterialsServiceModel.setDiamondOwn(planet.getDiamondOwn());
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

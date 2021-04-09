package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.enums.MaterialEnum;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
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
    private final PlanetService planetService;
    private final ModelMapper modelMapper;
    private PlanetResourceEntity planetResourceEntityForSchedule;

    public PlanetResourceServiceImpl(PlanetResourceRepository planetResourceRepository, PlanetService planetService, ModelMapper modelMapper) {
        this.planetResourceRepository = planetResourceRepository;
        this.planetService = planetService;
        this.modelMapper = modelMapper;
    }


    private PlanetResourceEntity createPlanetResourceEntity(PlanetEntity planetEntity) {
        PlanetResourceEntity current = seedCurrentResources(new PlanetResourceEntity());
        current.setPlanetEntity(planetEntity);
        planetResourceRepository.save(current);
        return current;

    }

    @Override
    public PlanetResourceModelInfo findById(Long planetEntityId) {
        Optional<PlanetResourceEntity> planetResource = planetResourceRepository.findById(planetEntityId);
        PlanetResourceEntity  planetResourceEntity;
        if(planetResource.isEmpty()){
           planetResourceEntity = createPlanetResourceEntity(planetService.findPlanetById(planetEntityId));
        }else{
          planetResourceEntity  = planetResource.get();
        }
        planetResourceEntityForSchedule= planetResourceEntity;
        return modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class);
    }

    @Override
    public void stopScheduleForPlanet() {
        planetResourceEntityForSchedule=null;
    }

    @Override
    public void increaseIncomesAndCapacity(PlanetResourceEntity planetResourceEntity, int increaseCapacity, int increaseIncomes, MaterialEnum material) {
        switch (material){
            case GAS:
                planetResourceEntity.setGasForMin(increaseIncomes);
                planetResourceEntity.setGasCapacity(increaseCapacity);
                break;
            case METAL:
                planetResourceEntity.setMetalForMin(increaseIncomes);
                planetResourceEntity.setMetalCapacity(increaseCapacity);
                break;
            case ENERGY:
                planetResourceEntity.setEnergyForMin(increaseIncomes);
                planetResourceEntity.setEnergyCapacity(increaseCapacity);
                break;
            case DIAMOND:
                planetResourceEntity.setDiamondForMin(increaseIncomes);
                planetResourceEntity.setDiamondCapacity(increaseCapacity);
                break;
        }
        planetResourceRepository.save(planetResourceEntity);
    }


    @Override
    public boolean changeMaterials(BankBindingModel bankBindingModel, PlanetResourceModelInfo currentPlanet) {
        PlanetResourceEntity planet = planetResourceRepository.findById(currentPlanet.getId()).orElseThrow();
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
            modelMapper.map(planet , PlanetResourceModelInfo.class);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }

    }

    public void increaseOwns(int i){
        if(planetResourceEntityForSchedule==null){
            return;
        }

        planetResourceEntityForSchedule.setMetalOwn(increaseSchedule(planetResourceEntityForSchedule.getMetalOwn()
                , planetResourceEntityForSchedule.getMetalForMin()*i, planetResourceEntityForSchedule.getMetalCapacity()));
        planetResourceEntityForSchedule.setDiamondOwn(increaseSchedule(planetResourceEntityForSchedule.getDiamondOwn()
                ,planetResourceEntityForSchedule.getDiamondForMin()*i, planetResourceEntityForSchedule.getDiamondCapacity()));
        planetResourceEntityForSchedule.setGasOwn(increaseSchedule(planetResourceEntityForSchedule.getGasOwn()
                , planetResourceEntityForSchedule.getGasForMin()*i, planetResourceEntityForSchedule.getGasCapacity()));
        planetResourceEntityForSchedule.setEnergyOwn(increaseSchedule(planetResourceEntityForSchedule.getEnergyOwn()
                ,planetResourceEntityForSchedule.getEnergyForMin()*i, planetResourceEntityForSchedule.getEnergyCapacity()));
        planetResourceRepository.save(planetResourceEntityForSchedule);
        modelMapper.map(planetResourceEntityForSchedule , PlanetResourceModelInfo.class);
    }


    @Override
    public void decreaseOwns(PlanetResourceEntity planet, int diamond, int energy, int metal, int gas) {
        planet.setMetalOwn(decreaseMaterials(planet.getMetalOwn(), metal));
        planet.setDiamondOwn(decreaseMaterials(planet.getDiamondOwn() , diamond));
        planet.setGasOwn(decreaseMaterials(planet.getGasOwn() , gas));
        planet.setEnergyOwn(decreaseMaterials(planet.getEnergyOwn() , energy));
        planetResourceRepository.save(planet);
        modelMapper.map(planet , PlanetResourceModelInfo.class);
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

    private static int increaseSchedule(int first, int second, int capacity) {
        if ((int) (first + second ) > capacity) {
            return capacity;
        } else {
            return (int) (first +second );
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

package com.example.demo.services.implementation;

import com.example.demo.items.stations.*;
import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetStationEntity;
import com.example.demo.models.entities.StationEntity;
import com.example.demo.models.entities.enums.MaterialEnum;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.StationViewModel;
import com.example.demo.repositories.PlanetStationRepository;
import com.example.demo.repositories.StationRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;
    private final PlanetStationRepository planetStationRepository;
    private final PlanetResourceService planetResourceService;
    private final ModelMapper modelMapper;

    public StationServiceImpl(StationRepository stationRepository, PlanetStationRepository planetStationRepository, PlanetResourceService planetResourceService, ModelMapper modelMapper) {
        this.stationRepository = stationRepository;
        this.planetStationRepository = planetStationRepository;
        this.planetResourceService = planetResourceService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seed() {
        StationEntity metalMine = new MetalStation();
        StationEntity gasMine = new GasStation();
        StationEntity diamondMine = new DiamondStation();
        StationEntity energyPanel = new EnergyStation();
        stationRepository.saveAll(List.of(metalMine, gasMine, diamondMine, energyPanel));
    }

    @Override
    public List<StationViewModel> getAllStationsByCurrentPlanet(PlanetResourceModelInfo planetResourceModelInfo) {
        List<StationViewModel> out = new ArrayList<>();

        List<PlanetStationEntity> allStations = planetStationRepository.findAllByPlanetResourceEntity(modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class));
        List<StationEntity> all = stationRepository.findAll();
        for (StationEntity stationEntity : all) {
            PlanetStationEntity planetStationEntity = null;
            for (PlanetStationEntity entity : allStations) {
                if (entity.getStationEntity().getName().equals(stationEntity.getName())) {
                    planetStationEntity = entity;
                    break;
                }
            }
            if (planetStationEntity == null) {
                planetStationEntity = new PlanetStationEntity();
                planetStationEntity.setLevel(0).setStationEntity(stationEntity).setPlanetResourceEntity(modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class));
                planetStationRepository.save(planetStationEntity);
            }
            StationViewModel mapped = new StationViewModel();
            double currentLevel = planetStationEntity.getLevel() * 0.1 + 1;
            mapped.setDiamond((int) Math.round(stationEntity.getDiamond() * currentLevel))
                    .setEnergy((int) Math.round(stationEntity.getEnergy() * currentLevel))
                    .setMetal((int) Math.round(stationEntity.getMetal() * currentLevel))
                    .setGas((int) Math.round(stationEntity.getGas() * currentLevel))
                    .setTime((int) Math.round(stationEntity.getTime() * currentLevel));
            mapped.setCurrentLevel(planetStationEntity.getLevel())
                    .setDescription(stationEntity.getDescription()).setName(stationEntity.getName());
            if (planetStationEntity.getId() != null) {
                mapped.setId(planetStationEntity.getId());
            }
            out.add(mapped);
        }
        return out;
    }

    @Override
    public void updateScienceLevel(Long id) {
        Optional<PlanetStationEntity> planetStations = planetStationRepository.findById(id);
        double level = planetStations.get().getLevel() * 0.1 + 1;
        StationEntity stationEntity = planetStations.get().getStationEntity();
        planetStations.get().setLevel(planetStations.get().getLevel() + 1);
        planetStationRepository.save(planetStations.get());
        PlanetResourceModelInfo planetResourceModelInfo = modelMapper.map(planetStations.get().getPlanetResourceEntity(), PlanetResourceModelInfo.class);
        planetResourceService.decreaseOwns(planetResourceModelInfo,
                (int) Math.round(stationEntity.getDiamond() * level),
                (int) Math.round(stationEntity.getEnergy() * level), (int) Math.round(stationEntity.getMetal() * level)
                , (int) Math.round(stationEntity.getGas() * level));
        BaseStation baseStation;
        try {
            baseStation = (BaseStation) stationEntity;
            if (baseStation instanceof MetalStation) {
                planetResourceService.increaseIncomesAndCapacity(planetResourceModelInfo, baseStation.increaseCapacity(planetResourceModelInfo.getMetalCapacity())
                        , baseStation.increaseIncomes(planetResourceModelInfo.getMetalForMin()), MaterialEnum.METAL);
            } else if (baseStation instanceof GasStation) {
                planetResourceService.increaseIncomesAndCapacity(planetResourceModelInfo
                        , baseStation.increaseCapacity(planetResourceModelInfo.getGasCapacity()),
                        baseStation.increaseIncomes(planetResourceModelInfo.getGasForMin()),
                        MaterialEnum.GAS);
            } else if (baseStation instanceof DiamondStation) {
                planetResourceService.increaseIncomesAndCapacity(planetResourceModelInfo,
                        baseStation.increaseCapacity(planetResourceModelInfo.getDiamondCapacity()),
                        baseStation.increaseIncomes(planetResourceModelInfo.getDiamondForMin()),
                        MaterialEnum.DIAMOND);
            } else if (baseStation instanceof EnergyStation) {
                planetResourceService.increaseIncomesAndCapacity(planetResourceModelInfo,
                        baseStation.increaseCapacity(planetResourceModelInfo.getEnergyCapacity()),
                        baseStation.increaseIncomes(planetResourceModelInfo.getEnergyForMin()),
                        MaterialEnum.ENERGY);
            }
        } catch (ClassCastException e) {

        }
    }

    @Override
    public PlanetStationEntity findByPlanetStationEntityId(long id) {
        return planetStationRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean createNewStation(AddingBindingModel addingBindingModel) {
        Optional<StationEntity> current = stationRepository.findFirstByName(addingBindingModel.getName());
        if (current.isEmpty()) {
            StationEntity stationEntity = modelMapper.map(addingBindingModel, StationEntity.class);
            stationRepository.save(stationEntity);
            return true;
        }
        return false;
    }
}

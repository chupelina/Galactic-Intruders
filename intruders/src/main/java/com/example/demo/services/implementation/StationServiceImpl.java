package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.*;
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
        StationEntity metalMine = new StationEntity();
        metalMine.setDescription("increase metal income with 5%").setDiamond(5).setEnergy(13)
                .setGas(13).setMetal(23).setName("Metal mine").setTime(10);
        StationEntity gasMine = new StationEntity();
        gasMine.setDescription("increase gas income with 5%").setDiamond(5).setEnergy(13)
                .setGas(13).setMetal(23).setName("Gas mine").setTime(10);
        StationEntity diamondMine = new StationEntity();
        diamondMine.setDescription("increase diamond income with 5%").setDiamond(5).setEnergy(13)
                .setGas(13).setMetal(23).setName("Diamond mine").setTime(10);
        StationEntity energyPanel = new StationEntity();
        energyPanel.setDescription("increase energy income with 5%").setDiamond(5).setEnergy(13)
                .setGas(13).setMetal(23).setName("Energy panel").setTime(10);
        stationRepository.saveAll(List.of(metalMine, gasMine, diamondMine, energyPanel));
    }

    @Override
    public List<StationViewModel> getAllStationsByCurrentPlanet(PlanetEntity planetEntity) {
        List<StationViewModel> out = new ArrayList<>();

        List<PlanetStationEntity> allStations = planetStationRepository.findAllByPlanetEntity(planetEntity);
        List<StationEntity> all = stationRepository.findAll();
        for (StationEntity stationEntity: all) {
            PlanetStationEntity planetStationEntity = null;
            for (PlanetStationEntity entity : allStations) {
                if (entity.getStationEntity().getName().equals(stationEntity.getName())) {
                    planetStationEntity = entity;
                    break;
                }
            }
            if (planetStationEntity == null) {
                planetStationEntity = new PlanetStationEntity();
                planetStationEntity.setLevel(0).setStationEntity(stationEntity).setPlanetEntity(planetEntity);
                planetStationRepository.save(planetStationEntity);
            }
            StationViewModel mapped = new StationViewModel();
            double currentLevel =planetStationEntity.getLevel()*0.1 +1;
            mapped.setDiamond((int)Math.round(stationEntity.getDiamond()*currentLevel))
                    .setEnergy((int)Math.round(stationEntity.getEnergy()*currentLevel))
                    .setMetal((int)Math.round(stationEntity.getMetal()*currentLevel))
                    .setGas((int)Math.round(stationEntity.getGas()*currentLevel))
                    .setTime((int)Math.round(stationEntity.getTime()*currentLevel));
            mapped.setCurrentLevel(planetStationEntity.getLevel())
                    .setId(planetStationEntity.getId())
                    .setDescription(stationEntity.getDescription()).setName(stationEntity.getName());
            out.add(mapped);
        }
        return out;
    }

    @Override
    public void updateScienceLevel(Long id) {
        Optional<PlanetStationEntity> planetStations = planetStationRepository.findById(id);
        double level = planetStations.get().getLevel()*0.1+1;
        StationEntity stationEntity = planetStations.get().getStationEntity();
        planetStations.get().setLevel(planetStations.get().getLevel()+1);
        planetStationRepository.save(planetStations.get());
        planetResourceService.decreaseOwns(planetStations.get().getPlanetEntity().getPlanetResourceEntity(),
                (int)Math.round(stationEntity.getDiamond()*level),
                (int)Math.round(stationEntity.getEnergy()*level), (int)Math.round(stationEntity.getMetal()*level)
                , (int)Math.round(stationEntity.getGas()*level));
    }

    @Override
    public void createStations(PlanetEntity planet) {
        List<StationEntity> allStations = stationRepository.findAll();
        for (int i = 0; i < allStations.size(); i++) {
            PlanetStationEntity planetStationEntity = new PlanetStationEntity();
            planetStationEntity.setLevel(0).setStationEntity(allStations.get(i)).setPlanetEntity(planet);
            planetStationRepository.save(planetStationEntity);
        }
    }

    @Override
    public boolean createOne(AddingBindingModel addingBindingModel) {
        Optional<StationEntity> current = stationRepository.findFirstByName(addingBindingModel.getName());
        if(current.isEmpty()){
            StationEntity stationEntity = modelMapper.map(addingBindingModel, StationEntity.class);
            stationRepository.save(stationEntity);
            return true;
        }
        return false;
    }
}

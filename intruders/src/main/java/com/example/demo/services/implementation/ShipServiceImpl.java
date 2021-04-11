package com.example.demo.services.implementation;


import com.example.demo.items.ships.*;
import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetShipEntity;
import com.example.demo.models.entities.ShipEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.ShipModel;
import com.example.demo.models.viewModels.ShipViewModel;
import com.example.demo.repositories.PlanetShipRepository;
import com.example.demo.repositories.ShipRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final PlanetShipRepository planetShipRepository;
    private final PlanetResourceService planetResourceService;

    public ShipServiceImpl(ShipRepository shipRepository, ModelMapper modelMapper
            , PlanetShipRepository planetShipRepository, PlanetResourceService planetResourceService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.planetShipRepository = planetShipRepository;
        this.planetResourceService = planetResourceService;

    }

    @Override
    public void seedShips() {
        if (shipRepository.count() != 0) {
            return;
        }
        ShipEntity cargoShip = new CargoShip();
        ShipEntity cyclone = new Cyclone();
        ShipEntity destroyer = new Destroyer();
        ShipEntity fieldFighter = new FieldFighter();
        ShipEntity guardian = new Guardian();
        ShipEntity scout = new Scout();
        System.out.println();
        shipRepository.saveAll(List.of(cyclone, cargoShip, destroyer, fieldFighter, guardian, scout));
    }

    @Override
    public List<ShipViewModel> getAllShipsByCurrentPlanet(PlanetResourceModelInfo planetResourceModelInfo) {
        List<ShipViewModel> allShips = new ArrayList<>();
        List<ShipEntity> shipEntityList = shipRepository.findAll();
        List<PlanetShipEntity> planetShipEntityList = planetShipRepository.findAllByPlanetResourceEntity(modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class));
        for (ShipEntity ship : shipEntityList) {
            PlanetShipEntity planetShipEntity = null;
            for (PlanetShipEntity shipEntity : planetShipEntityList) {
                if (ship.getName().equals(shipEntity.getShipEntity().getName())) {
                    planetShipEntity = shipEntity;
                }
            }
            if (planetShipEntity == null) {
                planetShipEntity = new PlanetShipEntity();
                planetShipEntity.setCountShips(0)
                        .setPlanetResourceEntity(modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class)).setShipEntity(ship);
                planetShipRepository.save(planetShipEntity);
            }
            ShipViewModel shipViewModel = modelMapper.map(ship, ShipViewModel.class);
            if (planetShipEntity.getId() != null) {
                shipViewModel.setCount(planetShipEntity.getCountShips()).setId(planetShipEntity.getId());
            }
            allShips.add(shipViewModel);
        }
        return allShips;
    }

    @Override
    public void addShips(Long shipId, int count) {
        PlanetShipEntity ship = planetShipRepository.findById(shipId).orElseThrow();
        ship.setCountShips(ship.getCountShips() + count);
        Optional<ShipEntity> shipNeeds = shipRepository.findById(ship.getShipEntity().getId());
        planetShipRepository.save(ship);
        PlanetResourceModelInfo map = modelMapper.map(ship.getPlanetResourceEntity(), PlanetResourceModelInfo.class);
        planetResourceService.decreaseOwns(map, shipNeeds.get().getDiamond() * count, shipNeeds.get().getEnergy() * count,
                shipNeeds.get().getMetal() * count, shipNeeds.get().getGas() * count);
        if (shipNeeds.get() instanceof CargoShip) {
            CargoShip cargoShip = (CargoShip) shipNeeds.get();
            planetResourceService.increaseIncomes(map, cargoShip.increaseIncomes(count, map.getMetalForMin(),
                    map.getGasForMin(), map.getDiamondForMin(), map.getEnergyForMin()));
        }
    }

    @Override
    public boolean createNewShip(AddingBindingModel addingBindingModel) {
        Optional<ShipEntity> current = shipRepository.findFirstByName(addingBindingModel.getName());
        if (current.isEmpty()) {
            ShipEntity shipEntity = modelMapper.map(addingBindingModel, ShipEntity.class);
            shipRepository.save(shipEntity);
            return true;
        }
        return false;
    }

    public PlanetShipEntity returnPlanetShipEntityById(long id) {
        return planetShipRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ShipModel> getArmy(PlanetResourceModelInfo planetModelInfo) {
        getAllShipsByCurrentPlanet(planetModelInfo);
        List<ShipModel> army = new ArrayList<>();
        List<PlanetShipEntity> planetShipEntityList = planetShipRepository.findAllByPlanetResourceEntity(modelMapper.map(planetModelInfo, PlanetResourceEntity.class));
        for (PlanetShipEntity planetShipEntity : planetShipEntityList) {
            BaseShip shipEntity = (BaseShip) planetShipEntity.getShipEntity();
            ShipModel shipModel = new ShipModel();
            shipModel.setAttack(shipEntity.getAttack())
                    .setCount(planetShipEntity.getCountShips())
                    .setHealth(shipEntity.getHealth())
                    .setName(shipEntity.getName());
            army.add(shipModel);
        }
        return army;
    }

    @Override
    public List<ShipModel> createEnemy() {
        List<ShipModel> enemy = new ArrayList<>();
       ShipEntity destroyer = new Destroyer();
       ShipEntity guardian = new Guardian();
        ShipModel dest = modelMapper.map(destroyer, ShipModel.class);
        ShipModel guard = modelMapper.map(guardian, ShipModel.class);
        dest.setGoneToBattle(3);
        guard.setGoneToBattle(4);
        enemy.add(dest);
        enemy.add(guard);
        return enemy;
    }

    @Override
    public void decreaseArmy(String ships, PlanetResourceModelInfo planetModelInfo) {
        String[] gone = ships.split("-");
        List<PlanetShipEntity> planetShipEntityList = planetShipRepository.findAllByPlanetResourceEntity(modelMapper.map(planetModelInfo, PlanetResourceEntity.class));
        for (PlanetShipEntity planetShipEntity : planetShipEntityList) {
            for (String s : gone) {
                String[] split = s.split(":");
                if(planetShipEntity.getShipEntity().getName().equals(split[0])){
                    planetShipEntity.setCountShips(planetShipEntity.getCountShips()-Integer.parseInt(split[2]));
                    planetShipRepository.save(planetShipEntity);
                    break;
                }
            }


        }
    }
}

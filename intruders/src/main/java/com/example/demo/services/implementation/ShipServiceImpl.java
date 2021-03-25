package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetShipEntity;
import com.example.demo.models.entities.ShipEntity;
import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
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
        ShipEntity cargoShip = new ShipEntity();
        cargoShip.setDescription("collect your incomes").setDiamond(20).setEnergy(20).setGas(5).setMetal(35).setImgUrl("/img/planets/cargoShip.jpg")
                .setName("Cargoship").setTime("10");
        ShipEntity cyclone = new ShipEntity();
        cyclone.setDescription("collect your incomes").setDiamond(25).setEnergy(10).setGas(10).setMetal(22).setImgUrl("/img/ships/cyclone.jpg")
                .setName("Cyclone").setTime("134");
        ShipEntity destroyer = new ShipEntity();
        destroyer.setDescription("collect your incomes").setDiamond(25).setEnergy(10).setGas(10).setMetal(22).setImgUrl("/img/ships/destroyer.jpg")
                .setName("Guardian").setTime("152");
        shipRepository.saveAll(List.of(cyclone, cargoShip, destroyer));
    }

    @Override
    public void createArmy(PlanetEntity planet) {
        List<ShipEntity> shipEntityList = shipRepository.findAll();
        for (ShipEntity shipEntity : shipEntityList) {
            PlanetShipEntity planetShipEntity = new PlanetShipEntity();
            planetShipEntity.setCountShips(0).setPlanetEntity(planet).setShipEntity(shipEntity);
            planetShipRepository.save(planetShipEntity);
        }
    }

    @Override
    public List<ShipViewModel> getAllScienceProjectsByCurrentPlanet(PlanetEntity planet) {
        List<ShipViewModel> allShips= new ArrayList<>();
        List<ShipEntity> shipEntityList = shipRepository.findAll();
        List<PlanetShipEntity> planetShipEntityList = planetShipRepository.findAllByPlanetEntity(planet);
        for (ShipEntity ship : shipEntityList) {
            PlanetShipEntity planetShipEntity=null;
            for (PlanetShipEntity shipEntity : planetShipEntityList) {
                if(ship.getName().equals(shipEntity.getShipEntity().getName())){
                    planetShipEntity=shipEntity;
                }
            }
            if(planetShipEntity==null){
                planetShipEntity = new PlanetShipEntity();
                planetShipEntity.setCountShips(0).setPlanetEntity(planet).setShipEntity(ship);
                planetShipRepository.save(planetShipEntity);
            }
            ShipViewModel shipViewModel = modelMapper.map(ship, ShipViewModel.class);
            shipViewModel.setCount(planetShipEntity.getCountShips()).setId(planetShipEntity.getId());
            allShips.add(shipViewModel);
        }
        return allShips;
    }

    @Override
    public void addShips(Long shipId, int count) {
        Optional<PlanetShipEntity> ship = planetShipRepository.findById(shipId);
        ship.get().setCountShips(ship.get().getCountShips()+count);
        Optional<ShipEntity> shipNeeds = shipRepository.findById(ship.get().getShipEntity().getId());
        planetResourceService.decreaseOwns(ship.get().getPlanetEntity().getPlanetResourceEntity(), shipNeeds.get().getDiamond()*count, shipNeeds.get().getEnergy()*count,
                shipNeeds.get().getMetal()*count, shipNeeds.get().getGas()*count);
        planetShipRepository.save(ship.get());
    }

    @Override
    public boolean createShip(AddingBindingModel addingBindingModel) {
        Optional<ShipEntity> current = shipRepository.findFirstByName(addingBindingModel.getName());
        if(current.isEmpty()){
            ShipEntity shipEntity = modelMapper.map(addingBindingModel, ShipEntity.class);
           shipRepository.save(shipEntity);
            return true;
        }
        return false;
    }
}
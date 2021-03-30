package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetShipEntity;
import com.example.demo.models.entities.ShipEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
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
        cargoShip.setDescription("collect your incomes").setDiamond(20).setEnergy(20).setGas(5).setMetal(35).setImgUrl("../img/ships/cargoship.jpg")
                .setName("Cargoship").setTime("10");
        ShipEntity cyclone = new ShipEntity();
        cyclone.setDescription("collect your incomes").setDiamond(25).setEnergy(10).setGas(10).setMetal(22).setImgUrl("../img/ships/cyclone.jpg")
                .setName("Cyclone").setTime("134");
        ShipEntity destroyer = new ShipEntity();
        destroyer.setDescription("collect your incomes").setDiamond(25).setEnergy(10).setGas(10).setMetal(22).setImgUrl("../img/ships/guardian.jpg")
                .setName("Guardian").setTime("152");
        shipRepository.saveAll(List.of(cyclone, cargoShip, destroyer));
    }

    @Override
    public List<ShipViewModel> getAllShipsByCurrentPlanet(PlanetResourceModelInfo planetResourceModelInfo) {
        List<ShipViewModel> allShips= new ArrayList<>();
        List<ShipEntity> shipEntityList = shipRepository.findAll();
        List<PlanetShipEntity> planetShipEntityList = planetShipRepository.findAllByPlanetResourceEntity(modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class));
        for (ShipEntity ship : shipEntityList) {
            PlanetShipEntity planetShipEntity=null;
            for (PlanetShipEntity shipEntity : planetShipEntityList) {
                if(ship.getName().equals(shipEntity.getShipEntity().getName())){
                    planetShipEntity=shipEntity;
                }
            }
            if(planetShipEntity==null){
                planetShipEntity = new PlanetShipEntity();
                planetShipEntity.setCountShips(0)
                        .setPlanetResourceEntity(modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class)).setShipEntity(ship);
                planetShipRepository.save(planetShipEntity);
            }
            ShipViewModel shipViewModel = modelMapper.map(ship, ShipViewModel.class);
            if(planetShipEntity.getId()!=null){
                shipViewModel.setCount(planetShipEntity.getCountShips()).setId(planetShipEntity.getId());
            }
            allShips.add(shipViewModel);
        }
        return allShips;
    }

    @Override
    public void addShips(Long shipId, int count) {
       PlanetShipEntity ship = planetShipRepository.findById(shipId).orElseThrow();
        ship.setCountShips(ship.getCountShips()+count);
        Optional<ShipEntity> shipNeeds = shipRepository.findById(ship.getShipEntity().getId());
        planetShipRepository.save(ship);

        planetResourceService.decreaseOwns(ship.getPlanetResourceEntity() , shipNeeds.get().getDiamond()*count, shipNeeds.get().getEnergy()*count,
                shipNeeds.get().getMetal()*count, shipNeeds.get().getGas()*count);
    }

    @Override
    public boolean createNewShip(AddingBindingModel addingBindingModel) {
        Optional<ShipEntity> current = shipRepository.findFirstByName(addingBindingModel.getName());
        if(current.isEmpty()){
            ShipEntity shipEntity = modelMapper.map(addingBindingModel, ShipEntity.class);
           shipRepository.save(shipEntity);
            return true;
        }
        return false;
    }

    public PlanetShipEntity returnPlanetShipEntityById(long id){
        return planetShipRepository.findById(id).orElseThrow();
    }
}

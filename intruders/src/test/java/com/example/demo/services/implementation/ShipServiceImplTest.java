package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetShipEntity;
import com.example.demo.models.entities.ShipEntity;
import com.example.demo.models.entities.enums.TypeOfMadeEnum;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.ShipViewModel;
import com.example.demo.repositories.PlanetShipRepository;
import com.example.demo.repositories.ShipRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.ShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ShipServiceImplTest {
    ShipService shipServiceTest;
    ShipEntity cargoShip;
    PlanetShipEntity planetShipEntity;
    PlanetResourceEntity planetResourceEntity;

    @Mock
    ShipRepository mockedShipRepository;
    @Autowired
    ModelMapper modelMapper;
    @Mock
    PlanetShipRepository mockedPlanetShipRepository;
    @Mock
    PlanetResourceService mockedPlanetResourceService;

    @BeforeEach
    public void setUp(){
        modelMapper = new ModelMapper();
        shipServiceTest = new ShipServiceImpl(mockedShipRepository, modelMapper, mockedPlanetShipRepository, mockedPlanetResourceService);
        cargoShip = new ShipEntity();
        cargoShip.setDescription("collect your incomes").setDiamond(20).setEnergy(20).setGas(5).setMetal(35).setImgUrl("/img/planets/cargoShip.jpg")
                .setName("Cargoship").setTime(10).setId(1L);
        planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(10).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(10).setDiamondForMin(10).setPlanetEntity(null).setId(1L);
        planetShipEntity = new PlanetShipEntity();
        planetShipEntity.setPlanetResourceEntity(planetResourceEntity).setCountShips(0).setShipEntity(cargoShip);

    }

    @Test
    void getAllScienceProjectsByCurrentPlanet() {
        lenient().when(mockedShipRepository.findAll()).thenReturn(List.of(cargoShip));
        lenient().when(mockedPlanetShipRepository.findAllByPlanetResourceEntity(planetResourceEntity)).thenReturn(List.of(planetShipEntity));
        lenient().when(mockedPlanetShipRepository.save(planetShipEntity)).thenReturn(planetShipEntity);

        List<ShipViewModel> allShipsByCurrentPlanet = shipServiceTest.getAllShipsByCurrentPlanet(modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class));
        assertEquals(allShipsByCurrentPlanet.get(0).getName(), cargoShip.getName());
    }

    @Test
    void addShipsWhenExisting() {
        Mockito.when(mockedPlanetShipRepository.findById(1L)).thenReturn(Optional.of(planetShipEntity));
        Mockito.when(mockedShipRepository.findById(1L)).thenReturn(Optional.of(cargoShip));
        shipServiceTest.addShips(1L,1);
        PlanetShipEntity expected = shipServiceTest.returnPlanetShipEntityById(1L);
        assertEquals(expected.getCountShips(), planetShipEntity.getCountShips() );
    }
    @Test
    void addShipsWhenNotExisting() {
        Mockito.when(mockedPlanetShipRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, ()->shipServiceTest.addShips(1L,1) );

    }
    @Test
    void createNewShipWhenNotExisting() {
        AddingBindingModel addingBindingModel = new AddingBindingModel();
        addingBindingModel.setTime(10).setName("aaa").setMetal(10).setGas(10).setEnergy(10).setDiamond(10).setDescription("something").setType(TypeOfMadeEnum.SHIP);
        Mockito.when(mockedShipRepository.findFirstByName("aaa")).thenReturn(Optional.empty());

        assertTrue(shipServiceTest.createNewShip(addingBindingModel));
    }
    @Test
    void createNewShipWhenExisting() {
        AddingBindingModel addingBindingModel = new AddingBindingModel();
        addingBindingModel.setTime(10).setName("aaa").setMetal(10).setGas(10).setEnergy(10).setDiamond(10).setDescription("something").setType(TypeOfMadeEnum.SHIP);
        Mockito.when(mockedShipRepository.findFirstByName("aaa")).thenReturn(Optional.of(cargoShip));

        assertFalse(shipServiceTest.createNewShip(addingBindingModel));
    }
}

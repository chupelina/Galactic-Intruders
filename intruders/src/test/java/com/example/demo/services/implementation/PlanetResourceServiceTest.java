package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.enums.MaterialEnum;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.repositories.PlanetResourceRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.PlanetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlanetResourceServiceTest {

    PlanetResourceEntity planetResourceEntity;
    @Autowired
    PlanetResourceService serviceToTest;
    @Mock
    PlanetResourceRepository mockedRepository;
    @Autowired
    PlanetService mockedPlanetService;
    @Autowired
    ModelMapper modelMapper;


    @BeforeEach
    public void setUp() {
        this.modelMapper = new ModelMapper();
        serviceToTest = new PlanetResourceServiceImpl(mockedRepository, mockedPlanetService, modelMapper);


        planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(20).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(20).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(20).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(20).setDiamondForMin(10);

    }

    @Test
    public void findByPlanetResourceId() {
        Mockito.when(mockedRepository.findById((long) 1)).thenReturn(Optional.of(planetResourceEntity));

        PlanetResourceModelInfo current = serviceToTest.findById((long) 1);

        Assertions.assertEquals(planetResourceEntity.getDiamondCapacity(), current.getDiamondCapacity());
        Assertions.assertEquals(planetResourceEntity.getEnergyOwn(), current.getEnergyOwn());

    }

//    @Test
//    public void findByPlanetResourceIdNotFound() {
//        Mockito.when(mockedRepository.findById((long)1)).thenReturn(Optional.empty());
//        PlanetEntity planetEntity = new PlanetEntity();
//        planetEntity.setDescription("lqlqlq").setImgUrl("123").setName("first").setUserEntity(null)
//                .setId((long)1);
//        Mockito.when(mockedPlanetService.findPlanetById(1)).thenReturn(planetEntity);
//
//        PlanetResourceModelInfo current = serviceToTest.findById((long) 1);
//
//        Assertions.assertEquals(mockedRepository.count(), 1);
//
//    }
    @Test
    public void decreaseOwnsTest() {

        PlanetResourceModelInfo planet = modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class);
        PlanetResourceModelInfo planetResourceModelInfo = serviceToTest.decreaseOwns(planet, 10, 10, 10, 10);
        Assertions.assertEquals(planetResourceModelInfo.getDiamondOwn(), 0);
        Assertions.assertEquals(planetResourceModelInfo.getEnergyOwn(), 0);
        Assertions.assertEquals(planetResourceModelInfo.getMetalOwn(), 0);
        Assertions.assertEquals(planetResourceModelInfo.getGasOwn(), 0);

    }

    @Test
    public void decreaseOwnsThrowsErrorTest() {
        PlanetResourceModelInfo planet = modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> serviceToTest.decreaseOwns(planet, 100, 100, 100, 100));

    }

    @Test
    public void materialsChange() {
        Mockito.when(mockedRepository.findById((long) 1)).thenReturn(Optional.of(planetResourceEntity));
        BankBindingModel bankBindingModel = new BankBindingModel();
        bankBindingModel.setCount(10).setWanted(MaterialEnum.DIAMOND).setType(MaterialEnum.ENERGY);

        PlanetResourceModelInfo planetServiceModel =modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class);
        planetServiceModel.setId((long)1);

        serviceToTest.changeMaterials(bankBindingModel, planetServiceModel);
        Assertions.assertEquals(planetResourceEntity.getEnergyOwn(), 0);
        Assertions.assertEquals(planetResourceEntity.getDiamondOwn(), 18);
    }

    @Test
    public void materialsChangeReturnTheCapacityTest() {
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(20).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(20).setDiamondForMin(10);
        Mockito.when(mockedRepository.findById((long) 1)).thenReturn(Optional.of(planetResourceEntity));
        BankBindingModel bankBindingModel = new BankBindingModel();
        bankBindingModel.setCount(10).setWanted(MaterialEnum.METAL).setType(MaterialEnum.GAS);

        PlanetResourceModelInfo planetServiceModel =modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class);
        planetServiceModel.setId((long)1);

        serviceToTest.changeMaterials(bankBindingModel, planetServiceModel);
        Assertions.assertEquals(planetResourceEntity.getGasOwn(), 0);
        Assertions.assertEquals(planetResourceEntity.getMetalOwn(), 10);
    }
    @Test
    public void materialsChangeThrowExceptionWhenNotEnoughMaterials() {
        planetResourceEntity.setEnergyOwn(0).setEnergyCapacity(20).setEnergyForMin(10)
                .setGasOwn(0).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(0).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(0).setDiamondCapacity(20).setDiamondForMin(10);
        Mockito.when(mockedRepository.findById((long) 1)).thenReturn(Optional.of(planetResourceEntity));
        BankBindingModel bankBindingModel = new BankBindingModel();
        bankBindingModel.setCount(10).setWanted(MaterialEnum.METAL).setType(MaterialEnum.GAS);

        PlanetResourceModelInfo planetServiceModel =modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class);
        planetServiceModel.setId((long)1);

        ;
        Assertions.assertFalse(serviceToTest.changeMaterials(bankBindingModel, planetServiceModel));
    }

}

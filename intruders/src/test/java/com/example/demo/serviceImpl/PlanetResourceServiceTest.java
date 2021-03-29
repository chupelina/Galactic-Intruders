package com.example.demo.serviceImpl;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.enums.MaterialEnum;
import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.repositories.PlanetResourceRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.PlanetService;
import com.example.demo.services.implementation.PlanetResourceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlanetResourceServiceTest {
    private PlanetResourceService serviceToTest;

    @Mock
    PlanetResourceRepository mockedRepository;
    @Mock
    PlanetService mockedPlanetService;
    @Mock
    PlanetModelInfo planetModelInfo;

    @BeforeEach
    public void setUp() {
        serviceToTest = new PlanetResourceServiceImpl(mockedRepository, planetModelInfo, mockedPlanetService);
        planetModelInfo = new PlanetModelInfo();
    }

    @Test
    public void findByPlanetResourceId() {
        PlanetResourceEntity planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(10).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(10).setDiamondForMin(10);
        Mockito.when(mockedRepository.findById((long) 1)).thenReturn(Optional.of(planetResourceEntity));

        PlanetResourceEntity current = serviceToTest.findById((long) 1);

        Assertions.assertEquals(planetResourceEntity.getDiamondCapacity(), current.getDiamondCapacity());
        Assertions.assertEquals(planetResourceEntity.getEnergyOwn(), current.getEnergyOwn());

    }

    @Test
    public void decreaseOwnsTest() {
        PlanetResourceEntity planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(10).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(10).setDiamondForMin(10);
        serviceToTest.decreaseOwns(planetResourceEntity, 10, 10, 10, 10);
        Assertions.assertEquals(planetResourceEntity.getDiamondOwn(), 0);
        Assertions.assertEquals(planetResourceEntity.getEnergyOwn(), 0);
        Assertions.assertEquals(planetResourceEntity.getMetalOwn(), 0);
        Assertions.assertEquals(planetResourceEntity.getGasOwn(), 0);

    }

    @Test
    public void decreaseOwnsThrowsErrorTest() {
        PlanetResourceEntity planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(10).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(10).setDiamondForMin(10);

        Assertions.assertThrows(IllegalArgumentException.class ,
                ()-> serviceToTest.decreaseOwns(planetResourceEntity, 100, 100, 100, 100) );

    }

    @Test
    public void materialsChange(){
        PlanetResourceEntity planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(10).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(10).setDiamondForMin(10);
        BankBindingModel bankBindingModel = new BankBindingModel();
        bankBindingModel.setCount(10).setWanted(MaterialEnum.DIAMOND).setType(MaterialEnum.ENERGY);
        Mockito.when(mockedRepository.findFirstByPlanetEntity_id((long) 1)).thenReturn(Optional.of(planetResourceEntity));
        PlanetServiceModel planetServiceModel = new PlanetServiceModel();
        serviceToTest.changeMaterials(bankBindingModel, planetServiceModel );
        Assertions.assertEquals(planetResourceEntity.getEnergyOwn(), 0);
        Assertions.assertEquals(planetResourceEntity.getDiamondOwn(), 18);
    }

}

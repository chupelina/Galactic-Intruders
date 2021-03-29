package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetStationEntity;
import com.example.demo.models.entities.StationEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.ScienceViewModel;
import com.example.demo.models.viewModels.StationViewModel;
import com.example.demo.repositories.PlanetStationRepository;
import com.example.demo.repositories.StationRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.StationService;
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
class StationServiceImplTest {
    StationService stationServiceTest;
    StationEntity metalMine;
    PlanetStationEntity planetStationEntity;
    PlanetResourceEntity planetResourceEntity;

    @Mock
    StationRepository mockedStationRepository;
    @Autowired
    ModelMapper modelMapper;
    @Mock
    PlanetStationRepository mockedPlanetStationRepository;
    @Mock
    PlanetResourceService mockedPlanetResourceService;

    @BeforeEach
    public void setUp(){
        modelMapper = new ModelMapper();
        stationServiceTest = new StationServiceImpl(mockedStationRepository, mockedPlanetStationRepository,
                mockedPlanetResourceService, modelMapper);
        metalMine = new StationEntity();
        metalMine.setDescription("increase metal income with 5%").setDiamond(5).setEnergy(13)
                .setGas(13).setMetal(23).setName("Metal mine").setTime(10).setId(1L);
        planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(10).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(10).setDiamondForMin(10).setPlanetEntity(null).setId(1L);
        planetStationEntity = new PlanetStationEntity();
        planetStationEntity.setPlanetResourceEntity(planetResourceEntity).setStationEntity(metalMine).setLevel(0).setId(1L);
    }

    @Test
    void getAllStationsByCurrentPlanet() {
        lenient().when(mockedPlanetStationRepository.findAllByPlanetResourceEntity(planetResourceEntity)).thenReturn(List.of(planetStationEntity));
        lenient().when(mockedStationRepository.findAll()).thenReturn(List.of(metalMine));
        lenient().when(mockedPlanetStationRepository.save(planetStationEntity)).thenReturn(planetStationEntity);
        List<StationViewModel> output = stationServiceTest.getAllStationsByCurrentPlanet(modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class));
        assertEquals(metalMine.getName(), output.get(0).getName());
        assertEquals(metalMine.getGas(), output.get(0).getGas());
    }

    @Test
    void updateScienceLevelWhenExisting() {
        Mockito.when(mockedPlanetStationRepository.findById(1L)).thenReturn(Optional.of(planetStationEntity));
        stationServiceTest.updateScienceLevel(1L);
        PlanetStationEntity expected = stationServiceTest.findByPlanetStationEntityId(1L);
        assertEquals(expected.getLevel(), 1);
    }

    @Test
    void updateScienceLevelWhenNotExisting() {
        Mockito.when(mockedPlanetStationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, ()-> stationServiceTest.updateScienceLevel(1L));
    }

    @Test
    void createNewStationWhenNotExisting() {
        AddingBindingModel addingBindingModel = new AddingBindingModel();
        addingBindingModel.setDescription("increase metal income with 5%").setDiamond(5).setEnergy(13)
                .setGas(13).setMetal(23).setName("Metal mine").setTime(10);
        Mockito.when(mockedStationRepository.findFirstByName("Metal mine")).thenReturn(Optional.empty());
        boolean newStation = stationServiceTest.createNewStation(addingBindingModel);
        assertTrue(newStation);
    }

    @Test
    void createNewStationWhenExisting() {
        AddingBindingModel addingBindingModel = new AddingBindingModel();
        addingBindingModel.setDescription("increase metal income with 5%").setDiamond(5).setEnergy(13)
                .setGas(13).setMetal(23).setName("Metal mine").setTime(10);
        Mockito.when(mockedStationRepository.findFirstByName("Metal mine")).thenReturn(Optional.of(metalMine));
        boolean newStation = stationServiceTest.createNewStation(addingBindingModel);
        assertFalse(newStation);
    }
}
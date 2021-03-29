package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetScienceEntity;
import com.example.demo.models.entities.ScienceEntity;
import com.example.demo.models.entities.enums.TypeOfMadeEnum;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.ScienceViewModel;
import com.example.demo.repositories.PlanetScienceRepository;
import com.example.demo.repositories.ScienceRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.ScienceService;
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
class ScienceServiceImplTest {
    ScienceService scienceServiceTest;
    ScienceEntity weaponProjectEntity;
    PlanetScienceEntity planetScienceEntity;
    PlanetResourceEntity planetResourceEntity;

    @Mock
    ScienceRepository mockedScienceRepository;
    @Autowired
    ModelMapper modelMapper;
    @Mock
    PlanetScienceRepository mockedPlanetScienceRepository;
    @Mock
    PlanetResourceService mockedPlanetResourceService;
    @BeforeEach
    public void setUp(){
        modelMapper = new ModelMapper();
        scienceServiceTest = new ScienceServiceImpl(mockedScienceRepository, modelMapper,
                mockedPlanetScienceRepository, mockedPlanetResourceService);
        weaponProjectEntity = new ScienceEntity();
        weaponProjectEntity.setDescription("Your weapons will be with 5% stronger")
                .setDiamond(10)
                .setEnergy(10)
                .setGas(10)
                .setMetal(10)
                .setTime(10)
                .setName("Weapon project").setId((long)1);
        planetResourceEntity = new PlanetResourceEntity();
        planetResourceEntity.setEnergyOwn(10).setEnergyCapacity(10).setEnergyForMin(10)
                .setGasOwn(10).setGasCapacity(10).setGasForMin(10)
                .setMetalOwn(10).setMetalCapacity(10).setMetalForMin(10)
                .setDiamondOwn(10).setDiamondCapacity(10).setDiamondForMin(10).setPlanetEntity(null).setId(1L);
        mockedScienceRepository.save(weaponProjectEntity);
        planetScienceEntity = new PlanetScienceEntity();
        planetScienceEntity.setPlanetResourceEntity(planetResourceEntity).setLevel(0).setScienceEntity(weaponProjectEntity).setId((long)1);
        mockedPlanetScienceRepository.save(planetScienceEntity);
    }

    @Test
    void getAllScienceProjectsByCurrentPlanet() {
        lenient().when(mockedPlanetScienceRepository.findAllByPlanetResourceEntity(planetResourceEntity)).thenReturn(List.of(planetScienceEntity));
        lenient().when(mockedScienceRepository.findAll()).thenReturn(List.of(weaponProjectEntity));
        lenient().when(mockedPlanetScienceRepository.save(planetScienceEntity)).thenReturn(planetScienceEntity);
        List<ScienceViewModel> output = scienceServiceTest.getAllScienceProjectsByCurrentPlanet(modelMapper.map(planetResourceEntity, PlanetResourceModelInfo.class));
        assertEquals(weaponProjectEntity.getName(), output.get(0).getName());
        assertEquals(weaponProjectEntity.getGas(), output.get(0).getGas());
    }

    @Test
    void updateScienceLevelWithExistingEntity() {
        Mockito.when(mockedPlanetScienceRepository.findById((long)1)).thenReturn(Optional.of(planetScienceEntity));
        scienceServiceTest.updateScienceLevel((long)1);
        PlanetScienceEntity planetScienceEntityById = scienceServiceTest.getPlanetScienceEntityById((long) 1);
        assertEquals(planetScienceEntityById.getLevel(), 1);

    }
    @Test
    void updateScienceLevelWithNotExistingEntity() {
        Mockito.when(mockedPlanetScienceRepository.findById((long)1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, ()->  scienceServiceTest.updateScienceLevel((long)1));

    }

    @Test
    void createNewScienceWhenNotExisting() {
        AddingBindingModel addingBindingModel = new AddingBindingModel();
        addingBindingModel.setTime(10).setName("aaa").setMetal(10).setGas(10).setEnergy(10).setDiamond(10).setDescription("something").setType(TypeOfMadeEnum.SCIENCE);

        Mockito.when(mockedScienceRepository.findFirstByName(addingBindingModel.getName())).thenReturn(Optional.empty());

        boolean newScience = scienceServiceTest.createNewScience(addingBindingModel);
        assertTrue(newScience);
    }
    @Test
    void createNewScienceWhenExisting() {
        AddingBindingModel addingBindingModel = new AddingBindingModel();
        addingBindingModel.setTime(10).setName("aaa").setMetal(10).setGas(10).setEnergy(10).setDiamond(10).setDescription("something").setType(TypeOfMadeEnum.SCIENCE);

        Mockito.when(mockedScienceRepository.findFirstByName(addingBindingModel.getName())).thenReturn(Optional.of(weaponProjectEntity));

        boolean newScience = scienceServiceTest.createNewScience(addingBindingModel);
        assertFalse(newScience);
    }
}
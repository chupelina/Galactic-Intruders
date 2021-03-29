package com.example.demo.services.implementation;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.repositories.PlanetRepository;
import com.example.demo.services.PlanetService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlanetServiceImplTest {
    PlanetService planetService;
    PlanetEntity planetEntity;

    @Mock
     PlanetRepository mockedPlanetRepository;
    @Autowired
     ModelMapper mockedModelMapper;
    @Mock
     UserService mockedUserService;

    @BeforeEach
    public void setUP(){
        mockedModelMapper = new ModelMapper();
        planetService = new PlanetServiceImpl(mockedPlanetRepository, mockedModelMapper, mockedUserService);
        planetEntity = new PlanetEntity();
        planetEntity.setName("aaa").setImgUrl("bbb").setDescription("ccc").setUserEntity(null);
    }

    @Test
    void getNewPlanetWhenUserNotExistsTest() {
        Mockito.when(mockedPlanetRepository.findByUserEntity(null)).thenReturn(Optional.empty());
        PlanetServiceModel current = planetService.getCurrentPlanet("pesho");
        List<String> descriptions = List.of("Something special....", "Something different....");
        List<String> names = List.of("dodo", "coco");

        assertTrue(names.contains(current.getName()));
        assertTrue(descriptions.contains(current.getDescription()));
    }

    @Test
    void getNewPlanetWhenUserExistsTest() {
        Mockito.when(mockedPlanetRepository.findByUserEntity(null)).thenReturn(Optional.of(planetEntity));
        PlanetServiceModel current = planetService.getCurrentPlanet("pesho");
        assertEquals("aaa",current.getName());
        assertEquals("ccc",current.getDescription());
    }
    @Test
    void findPlanetByIdWhenSuchExistsTest() {
        Mockito.when(mockedPlanetRepository.findById((long)1)).thenReturn(Optional.of(planetEntity));
        PlanetEntity current = planetService.findPlanetById((long)1);
        assertEquals("aaa",current.getName());
        assertEquals("ccc",current.getDescription());
    }

    @Test
    void findPlanetByIdWhenSuchNotExistsTest() {
        Mockito.when(mockedPlanetRepository.findById((long)1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, ()-> planetService.findPlanetById((long)1));
    }
}
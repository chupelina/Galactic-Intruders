package com.example.demo.services.implementation;

import com.example.demo.repositories.PlanetScienceRepository;
import com.example.demo.repositories.ScienceRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.ScienceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ScienceServiceImplTest {
    ScienceService scienceServiceTest;

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
        scienceServiceTest = new ScienceServiceImpl(mockedScienceRepository, modelMapper,
                mockedPlanetScienceRepository, mockedPlanetResourceService);
    }

    @Test
    void getAllScienceProjectsByCurrentPlanet() {
    }

    @Test
    void updateScienceLevel() {
    }

    @Test
    void createNewScience() {
    }
}
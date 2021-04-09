package com.example.demo.services.implementation;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.repositories.PlanetRepository;
import com.example.demo.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepository planetRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public PlanetServiceImpl(PlanetRepository planetRepository, ModelMapper modelMapper, UserService userService) {
        this.planetRepository = planetRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;

    }

    private PlanetServiceModel createPlanet(String username) {
        UserEntity user = modelMapper.map(userService.findByUsername(username), UserEntity.class);
        PlanetEntity planet = new PlanetEntity();
        planet.setDescription(getDescription())
                .setImgUrl(getImageUrl())
                .setName(getName())
                .setUserEntity(user);
        planetRepository.save(planet);
        return modelMapper.map(planet, PlanetServiceModel.class);
    }

    @Override
    public PlanetServiceModel getCurrentPlanet(String username) {

        UserEntity user = modelMapper.map(userService.findByUsername(username), UserEntity.class);
        Optional<PlanetEntity> planet = planetRepository.findByUserEntity(user);
        if (planet.isEmpty()) {
           return createPlanet(username);
        }
        return modelMapper.map(planet.get(), PlanetServiceModel.class);
    }

    @Override
    public PlanetEntity findPlanetById(long planetId) {
        return planetRepository.findById(planetId).orElseThrow();
    }

    private String getName() {
        // TODO: 19.3.2021 fill with data
        List<String> names = List.of("dodo", "coco");
        Random random = new Random();
        int current = random.nextInt(names.size());
        return names.get(current);
    }

    private String getImageUrl() {
        List<String> images = List.of("/img/planets/planet-1.jpg", "/img/planets/planet-2.jpg", "/img/planets/planet-3.png",
                "/img/planets/planet-4.jpg", "/img/planets/planet-5.jpg", "/img/planets/planet-6.jpg", "/img/planets/planet-7.jpg"
                , "/img/planets/planet-8.jpg", "/img/planets/planet-9.jpg", "/img/planets/planet-10.jpg");
        Random random = new Random();
        int current = random.nextInt(images.size());
        return images.get(current);
    }

    private String getDescription() {
        // TODO: 19.3.2021 fill with data
        List<String> descriptions = List.of("Something special....", "Something different....");
        Random random = new Random();
        int current = random.nextInt(descriptions.size());
        return descriptions.get(current);
    }


}

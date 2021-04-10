package com.example.demo.services.implementation;

import com.example.demo.items.science.EnergyProject;
import com.example.demo.items.science.WeaponProject;
import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetScienceEntity;
import com.example.demo.models.entities.ScienceEntity;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.ScienceViewModel;
import com.example.demo.repositories.PlanetScienceRepository;
import com.example.demo.repositories.ScienceRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.ScienceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ScienceServiceImpl implements ScienceService {
    private final ScienceRepository scienceRepository;
    private final ModelMapper modelMapper;
    private final PlanetScienceRepository planetScienceRepository;
    private final PlanetResourceService planetResourceService;

    public ScienceServiceImpl(ScienceRepository scienceRepository, ModelMapper modelMapper,
                              PlanetScienceRepository planetScienceRepository, PlanetResourceService planetResourceService) {
        this.scienceRepository = scienceRepository;
        this.modelMapper = modelMapper;
        this.planetScienceRepository = planetScienceRepository;
        this.planetResourceService = planetResourceService;
    }

    @Override
    public void seed() {
        ScienceEntity scienceEntity1=  new EnergyProject();
        ScienceEntity scienceEntity2 = new WeaponProject();
        scienceRepository.saveAll(List.of( scienceEntity1, scienceEntity2));
    }

    @Override
    public List<ScienceViewModel> getAllScienceProjectsByCurrentPlanet(PlanetResourceModelInfo planetResourceModelInfo) {
        List<ScienceViewModel> out = new ArrayList<>();

        List<PlanetScienceEntity> allScienceProjectsEntity = planetScienceRepository.findAllByPlanetResourceEntity(modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class));
        List<ScienceEntity> all = scienceRepository.findAll();
        for (ScienceEntity scienceEntity : all) {
            PlanetScienceEntity planetScienceEntity = null;
            for (PlanetScienceEntity entity : allScienceProjectsEntity) {
                if (entity.getScienceEntity().getName().equals(scienceEntity.getName())) {
                    planetScienceEntity = entity;
                    break;
                }
            }
            if (planetScienceEntity == null) {
                planetScienceEntity = createPlanetScienceEntity(scienceEntity, modelMapper.map(planetResourceModelInfo, PlanetResourceEntity.class));
            }
            ScienceViewModel mapped = creatViewModel(planetScienceEntity.getLevel(), scienceEntity, planetScienceEntity);
            out.add(mapped);

        }
        return out;
    }

    private PlanetScienceEntity createPlanetScienceEntity(ScienceEntity scienceEntity, PlanetResourceEntity planetResourceEntity) {
        PlanetScienceEntity planetScienceEntity = new PlanetScienceEntity();
        planetScienceEntity.setLevel(0).setScienceEntity(scienceEntity).
                setPlanetResourceEntity(planetResourceEntity);
        planetScienceRepository.save(planetScienceEntity);
        return planetScienceEntity;
    }

    private ScienceViewModel creatViewModel(int level, ScienceEntity scienceEntity, PlanetScienceEntity planetScienceEntity) {

        ScienceViewModel mapped = new ScienceViewModel();
        double currentLevel = level * 0.1 + 1;
        mapped.setDiamond((int) Math.round(scienceEntity.getDiamond() * currentLevel))
                .setEnergy((int) Math.round(scienceEntity.getEnergy() * currentLevel))
                .setMetal((int) Math.round(scienceEntity.getMetal() * currentLevel))
                .setGas((int) Math.round(scienceEntity.getGas() * currentLevel))
                .setTime((int) Math.round(scienceEntity.getTime() * currentLevel)).setName(scienceEntity.getName());
        if (planetScienceEntity.getId() != null) {
            mapped.setCurrentLevel(planetScienceEntity.getLevel()).setId(planetScienceEntity.getId())
                    .setDescription(scienceEntity.getDescription());
        }
        return mapped;
    }

    @Override
    public void updateScienceLevel(Long id) {
        PlanetScienceEntity planetScience = planetScienceRepository.findById(id).orElseThrow();
        double level = planetScience.getLevel() * 0.1 + 1;
        ScienceEntity scienceEntity = planetScience.getScienceEntity();
        planetScience.setLevel(planetScience.getLevel() + 1);
        planetScienceRepository.save(planetScience);
        PlanetResourceModelInfo map = modelMapper.map(planetScience.getPlanetResourceEntity(), PlanetResourceModelInfo.class);
        planetResourceService.decreaseOwns(map,
                (int) Math.round(scienceEntity.getDiamond() * level),
                (int) Math.round(scienceEntity.getEnergy() * level), (int) Math.round(scienceEntity.getMetal() * level)
                , (int) Math.round(scienceEntity.getGas() * level));

    }

    @Override
    public boolean createNewScience(AddingBindingModel addingBindingModel) {
        Optional<ScienceEntity> current = scienceRepository.findFirstByName(addingBindingModel.getName());
        if (current.isEmpty()) {
            ScienceEntity scienceEntity = modelMapper.map(addingBindingModel, ScienceEntity.class);
            scienceRepository.save(scienceEntity);
            return true;
        }
        return false;
    }

    public PlanetScienceEntity getPlanetScienceEntityById(long id) {
        return planetScienceRepository.findById(id).orElseThrow();
    }
}
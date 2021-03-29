package com.example.demo.services.implementation;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetScienceEntity;
import com.example.demo.models.entities.ScienceEntity;
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
        ScienceEntity weaponProjectEntity = new ScienceEntity();
        weaponProjectEntity.setDescription("Your weapons will be with 5% stronger")
                .setDiamond(20)
                .setEnergy(15)
                .setGas(17)
                .setMetal(50)
                .setTime(162)
                .setName("Weapon project");
        ScienceEntity energyProject = new ScienceEntity();
        energyProject.setDescription("Your energy will increase with 5%")
                .setDiamond(10)
                .setEnergy(0)
                .setGas(12)
                .setMetal(35)
                .setTime(10)
                .setName("Energy project");
        scienceRepository.saveAll(List.of(weaponProjectEntity, energyProject));
    }

    @Override
    public List<ScienceViewModel> getAllScienceProjectsByCurrentPlanet(Long planetEntityId) {
        List<ScienceViewModel> out = new ArrayList<>();

        List<PlanetScienceEntity> allScienceProjectsEntity = planetScienceRepository.findAllByPlanetResourceEntity_Id(planetEntityId);
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
                planetScienceEntity = new PlanetScienceEntity();
                planetScienceEntity.setLevel(0).setScienceEntity(scienceEntity).setPlanetResourceEntity(modelMapper.map(planetResourceService.findById(planetEntityId), PlanetResourceEntity.class));
                planetScienceRepository.save(planetScienceEntity);
            }
            ScienceViewModel mapped = new ScienceViewModel();
            double currentLevel =planetScienceEntity.getLevel()*0.1 +1;
            mapped.setDiamond((int)Math.round(scienceEntity.getDiamond()*currentLevel))
                    .setEnergy((int)Math.round(scienceEntity.getEnergy()*currentLevel))
                    .setMetal((int)Math.round(scienceEntity.getMetal()*currentLevel))
                    .setGas((int)Math.round(scienceEntity.getGas()*currentLevel))
            .setTime((int)Math.round(scienceEntity.getTime()*currentLevel));
            mapped.setCurrentLevel(planetScienceEntity.getLevel())
                    .setId(planetScienceEntity.getId())
            .setDescription(scienceEntity.getDescription()).setName(scienceEntity.getName());
            out.add(mapped);

        }
        return out;
    }


    @Override
    public void updateScienceLevel(Long id) {
        Optional<PlanetScienceEntity> planetScience = planetScienceRepository.findById(id);
        double level = planetScience.get().getLevel()*0.1+1;
        ScienceEntity scienceEntity = planetScience.get().getScienceEntity();
        planetScience.get().setLevel(planetScience.get().getLevel()+1);
        planetScienceRepository.save(planetScience.get());
        planetResourceService.decreaseOwns( planetScience.get().getPlanetResourceEntity(),
                (int)Math.round(scienceEntity.getDiamond()*level),
                (int)Math.round(scienceEntity.getEnergy()*level), (int)Math.round(scienceEntity.getMetal()*level)
                , (int)Math.round(scienceEntity.getGas()*level));

    }

    @Override
    public boolean createNewScience(AddingBindingModel addingBindingModel) {
        Optional<ScienceEntity> current = scienceRepository.findFirstByName(addingBindingModel.getName());
        if(current.isEmpty()){
            ScienceEntity scienceEntity = modelMapper.map(addingBindingModel, ScienceEntity.class);
            scienceRepository.save(scienceEntity);
            return true;
        }
        return false;
    }
}

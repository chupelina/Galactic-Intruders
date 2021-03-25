package com.example.demo.repositories;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetScienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanetScienceRepository extends JpaRepository<PlanetScienceEntity, Long> {
    List<PlanetScienceEntity> findAllByPlanetResourceEntity_Id(Long planetEntityId);


}

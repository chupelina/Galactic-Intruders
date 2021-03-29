package com.example.demo.repositories;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetStationRepository extends JpaRepository<PlanetStationEntity, Long> {
    List<PlanetStationEntity> findAllByPlanetResourceEntity(PlanetResourceEntity planetResourceEntity);
}

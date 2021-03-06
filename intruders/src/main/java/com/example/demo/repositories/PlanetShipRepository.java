package com.example.demo.repositories;

import com.example.demo.models.entities.PlanetResourceEntity;
import com.example.demo.models.entities.PlanetShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetShipRepository extends JpaRepository<PlanetShipEntity, Long> {
    List<PlanetShipEntity> findAllByPlanetResourceEntity(PlanetResourceEntity planetResourceEntity);
}

package com.example.demo.repositories;

import com.example.demo.models.entities.PlanetResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetResourceRepository extends JpaRepository<PlanetResourceEntity, Long> {

}

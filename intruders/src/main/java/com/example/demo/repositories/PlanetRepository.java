package com.example.demo.repositories;

import com.example.demo.models.entities.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<PlanetEntity, Long> {
    Optional<PlanetEntity> findByUserEntity_Username (String username );
}

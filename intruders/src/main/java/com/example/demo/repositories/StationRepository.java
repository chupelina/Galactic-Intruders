package com.example.demo.repositories;

import com.example.demo.models.entities.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {
    Optional<StationEntity> findFirstByName(String name);
}

package com.example.demo.repositories;

import com.example.demo.models.entities.ScienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScienceRepository extends JpaRepository<ScienceEntity, Long> {
    Optional<ScienceEntity> findFirstByName(String name);
}

package com.example.demo.repositories;

import com.example.demo.models.entities.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    Optional<ShipEntity> findFirstByName(String name);
}

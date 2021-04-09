package com.example.demo.repositories;

import com.example.demo.models.entities.UserLogoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLogoutRepository extends JpaRepository<UserLogoutEntity, Long> {

    Optional<UserLogoutEntity> findByUser_Username(String username);
}

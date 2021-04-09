package com.example.demo.repositories;

import com.example.demo.models.entities.UserRoleEntity;
import com.example.demo.models.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findFirstByRole(RoleEnum role);
}

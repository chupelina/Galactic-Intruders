package com.example.demo.services;

import com.example.demo.models.entities.UserRoleEntity;
import com.example.demo.models.entities.enums.RoleEnum;

import java.util.List;

public interface UserRoleService {
    void seedUserRole();
    UserRoleEntity getRole(String role);

}

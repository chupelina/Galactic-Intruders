package com.example.demo.services;

import com.example.demo.models.entities.UserRoleEntity;

public interface UserRoleService {
    void seedUserRole();
    UserRoleEntity getRole(String role);

}

package com.example.demo.services;

import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.models.serviceModels.UserRegisterServiceModel;

import java.util.List;

public interface UserService {
    void getFirstOne();

    UserEntity findByUsername(String s);

    boolean hasSameUsername(String username);

    void registerUser(UserRegisterServiceModel userRegisterServiceModel);

    List<String> getAllUsers();

    void changeUserRole(String username, String role);

}

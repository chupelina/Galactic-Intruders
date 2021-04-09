package com.example.demo.services;

import com.example.demo.models.serviceModels.UserServiceModel;

import java.util.List;

public interface UserService {
    void getFirstOne();

    UserServiceModel findByUsername(String s);

    boolean hasSameUsername(String username);

    void registerUser(UserServiceModel userServiceModel);

    List<String> getAllUsers();

    void changeUserRole(String username, String role);

}

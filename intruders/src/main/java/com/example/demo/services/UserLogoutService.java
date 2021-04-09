package com.example.demo.services;

public interface UserLogoutService {
    void saveUserLogout(String name);

    boolean includesUser(String remoteUser);

    void increaseOwns(String remoteUser);
}

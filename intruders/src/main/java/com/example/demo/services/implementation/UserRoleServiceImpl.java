package com.example.demo.services.implementation;

import com.example.demo.models.entities.UserRoleEntity;
import com.example.demo.models.entities.enums.RoleEnum;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void seedUserRole() {
        if(userRoleRepository.count()!=0){
            return;
        }
        UserRoleEntity admin = new UserRoleEntity();
        admin.setRole(RoleEnum.valueOf("ADMIN"));
        UserRoleEntity user = new UserRoleEntity();
        user.setRole(RoleEnum.valueOf("USER"));
        userRoleRepository.saveAll(List.of(admin, user));
    }

    @Override
    public UserRoleEntity getRole(String role) {
        return userRoleRepository.findFirstByRole(RoleEnum.valueOf(role));
    }

}

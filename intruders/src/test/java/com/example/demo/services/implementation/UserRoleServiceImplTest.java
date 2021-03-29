package com.example.demo.services.implementation;

import com.example.demo.models.entities.UserRoleEntity;
import com.example.demo.models.entities.enums.RoleEnum;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {
     UserRoleService userRoleServiceToTest;
     UserRoleEntity admin;

    @Mock
    UserRoleRepository mockedUserRoleRepository;

    @BeforeEach
    public void setUp(){
        userRoleServiceToTest = new UserRoleServiceImpl(mockedUserRoleRepository);
        admin = new UserRoleEntity();
        admin.setRole(RoleEnum.ADMIN);
    }

    @Test
    void getWhitExistingRole() {
        Mockito.when(mockedUserRoleRepository.findFirstByRole(RoleEnum.ADMIN)).thenReturn(admin);
        UserRoleEntity expected = userRoleServiceToTest.getRole("ADMIN");
        assertEquals(expected.getRole(), admin.getRole());
        assertThrows(IllegalArgumentException.class, ()->  userRoleServiceToTest.getRole("admin"));
    }

}
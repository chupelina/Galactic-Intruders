package com.example.demo.services.implementation;

import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.entities.UserRoleEntity;
import com.example.demo.models.entities.enums.RoleEnum;
import com.example.demo.models.serviceModels.UserServiceModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserRoleService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    UserService serviceToTest;
    UserEntity pesho;
    UserEntity gosho;
    UserRoleEntity admin;

    @Mock
    UserRepository mockedUserRepo;
    @Autowired
    UserRoleService mockedUserRoleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Mock
    UserSecurity userSecurity;
    @MockBean
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
         admin = new UserRoleEntity();
         modelMapper = new ModelMapper();
        admin.setRole(RoleEnum.ADMIN);
        UserRoleEntity user = new UserRoleEntity();
        user.setRole(RoleEnum.USER);
        passwordEncoder = new BCryptPasswordEncoder();
        serviceToTest = new UserServiceImpl(mockedUserRepo, mockedUserRoleService, passwordEncoder, userSecurity, modelMapper);
        pesho = new UserEntity();
        pesho.setUsername("pesho").setPassword("111").setEmail("222").setRoles(Set.of(admin));
        gosho = new UserEntity();
        gosho.setUsername("gosho").setPassword("333").setEmail("444").setRoles(Set.of(user));

    }


    @Test
    void findByUsernameWhenFound() {
        Mockito.when(mockedUserRepo.findFirstByUsername("gosho")).thenReturn(Optional.of(gosho));
        UserServiceModel current = modelMapper.map(serviceToTest.findByUsername("gosho"), UserServiceModel.class);
        assertEquals(current.getUsername(), gosho.getUsername());
    }
    @Test
    void findByUsernameWhenNotFound() {
        Mockito.when(mockedUserRepo.findFirstByUsername("gosho")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, ()-> serviceToTest.findByUsername("gosho"));
    }

    @Test
    void hasSameUsernameWhenNotSuch() {
        Mockito.when(mockedUserRepo.findFirstByUsername("gosho")).thenReturn(Optional.empty());
        boolean current = serviceToTest.hasSameUsername("gosho");
        assertTrue(current);
    }
    @Test
    void hasSameUsernameWhenSuch() {
        Mockito.when(mockedUserRepo.findFirstByUsername("gosho")).thenReturn(Optional.of(gosho));
        boolean current = serviceToTest.hasSameUsername("gosho");
        assertFalse(current);
    }

    @Test
    void getAllUsers() {
        Mockito.when(mockedUserRepo.findAll()).thenReturn(List.of(pesho, gosho));
        List<String> allUsers = serviceToTest.getAllUsers();
        assertEquals(allUsers.get(0), gosho.getUsername());
        assertEquals(allUsers.get(1), pesho.getUsername());
    }


}
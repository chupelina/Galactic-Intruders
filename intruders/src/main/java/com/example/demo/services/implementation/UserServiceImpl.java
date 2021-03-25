package com.example.demo.services.implementation;

import com.example.demo.models.entities.PlanetEntity;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.entities.UserRoleEntity;
import com.example.demo.models.serviceModels.UserRegisterServiceModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PlanetService;
import com.example.demo.services.UserRoleService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final UserSecurity userSecurity;
    private final PlanetService planetService;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService,
                           PasswordEncoder passwordEncoder, UserSecurity userSecurity, PlanetService planetService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.userSecurity = userSecurity;
        this.planetService = planetService;
    }

    @Override
    public void getFirstOne() {
        if (userRepository.count() != 0) {
            return;
        }
        UserEntity userEntity = new UserEntity();
        UserRoleEntity userRoleEntity = userRoleService.getRole("ADMIN");
        userEntity.setEmail("admin@abv.bg")
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("admin"))
                .setRoles(Set.of(userRoleEntity))
                .setPlanet(planetService.createPlanet());
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByUsername(String s) {
        //TODO: error handling
        return userRepository.findFirstByUsername(s).orElse(null);
    }

    @Override
    public boolean hasSameUsername(String username) {
        return userRepository.findFirstByUsername(username).isEmpty();
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRegisterServiceModel.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setEmail(userRegisterServiceModel.getEmail())
                .setRoles(Set.of(userRoleService.getRole("USER")))
                .setPlanet(planetService.createPlanet());

        userRepository.save(userEntity);
        UserDetails principal = userSecurity.loadUserByUsername(userEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public List<String> getAllUsers() {
        return userRepository.findAll().stream().map(UserEntity::getUsername).collect(Collectors.toList());
    }

    @Override
    public void changeUserRole(String username, String role) {
        Optional<UserEntity> current = userRepository.findFirstByUsername(username);
        if(current.isPresent()){
            Set<UserRoleEntity> roles = current.get().getRoles();
            roles.add(userRoleService.getRole(role.toUpperCase()));
            current.get().setRoles(roles);
            userRepository.save(current.get());
        }
    }

    @Override
    public PlanetEntity findById(long planetId) {
        return planetService.findPlanetById(planetId);
    }
}

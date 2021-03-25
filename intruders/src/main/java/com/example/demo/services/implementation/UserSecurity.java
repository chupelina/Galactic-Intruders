package com.example.demo.services.implementation;

import com.example.demo.models.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PlanetService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSecurity implements UserDetailsService {

    private final UserRepository userRepository;
    private final PlanetService planetService;

    public UserSecurity(UserRepository userRepository, PlanetService planetService) {
        this.userRepository = userRepository;

        this.planetService = planetService;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByUsername(s)
                .orElseThrow(()->new UsernameNotFoundException("User with this name does not exists!"));
        planetService.getCurrentPlanet(userEntity.getPlanet());
        return mapUser(userEntity);
    }

    private UserDetails mapUser(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(ur-> new SimpleGrantedAuthority("ROLE_"+ur.getRole()))
                .collect(Collectors.toList());
       return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}

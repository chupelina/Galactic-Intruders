package com.example.demo.services.implementation;

import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.entities.UserLogoutEntity;
import com.example.demo.models.serviceModels.UserServiceModel;
import com.example.demo.repositories.UserLogoutRepository;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.UserLogoutService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserLogoutServiceImpl implements UserLogoutService {

    private final UserLogoutRepository userLogoutRepository;
    private final UserService userService;
    private final PlanetResourceService planetResourceService;
    private final ModelMapper modelMapper;

    public UserLogoutServiceImpl(UserLogoutRepository userLogoutRepository, UserService userService, PlanetResourceService planetResourceService, ModelMapper modelMapper) {
        this.userLogoutRepository = userLogoutRepository;
        this.userService = userService;
        this.planetResourceService = planetResourceService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveUserLogout(String name) {
        UserServiceModel byUsername = userService.findByUsername(name);
        UserLogoutEntity userLogoutEntity = new UserLogoutEntity();
        userLogoutEntity.setLogoutDate(LocalDateTime.now()).setUser(modelMapper.map(byUsername, UserEntity.class));
        userLogoutRepository.save(userLogoutEntity);
    }

    @Override
    public boolean includesUser(String remoteUser) {
        Optional<UserLogoutEntity> user = userLogoutRepository.findByUser_Username(remoteUser);
        return user.isPresent();
    }

    @Override
    public void increaseOwns(String remoteUser) {
        Optional<UserLogoutEntity> user = userLogoutRepository.findByUser_Username(remoteUser);
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime logoutDate = user.get().getLogoutDate();
        int days ;
        if(currentDate.getDayOfYear() - logoutDate.getDayOfYear()<0){
            days = 365-logoutDate.getDayOfYear()+currentDate.getDayOfYear();
        }else{
            days = currentDate.getDayOfYear() - logoutDate.getDayOfYear();
        }
        int minutes = currentDate.getMinute() - logoutDate.getMinute() + (currentDate.getHour() - logoutDate.getHour()) * 60
                + days * 24 * 60;
        userLogoutRepository.delete(user.get());
        planetResourceService.increaseOwns(minutes);
    }
}

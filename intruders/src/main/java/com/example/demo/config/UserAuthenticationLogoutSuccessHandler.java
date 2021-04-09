package com.example.demo.config;

import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.UserLogoutService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationLogoutSuccessHandler implements LogoutHandler {

    private final UserLogoutService userLogoutService;
    private final PlanetResourceService planetResourceService;

    public UserAuthenticationLogoutSuccessHandler(UserLogoutService userLogoutService, PlanetResourceService planetResourceService) {
        this.userLogoutService = userLogoutService;
        this.planetResourceService = planetResourceService;
    }


    @Override
    public void logout(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       Authentication authentication) {
        userLogoutService.saveUserLogout(authentication.getName());
        planetResourceService.stopScheduleForPlanet();
    }
}

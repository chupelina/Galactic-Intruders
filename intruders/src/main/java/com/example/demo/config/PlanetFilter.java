package com.example.demo.config;


import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.PlanetService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class PlanetFilter implements Filter {
    private final PlanetService planetService;
    private final PlanetResourceService planetResourceService;

    public PlanetFilter(PlanetService planetService, PlanetResourceService planetResourceService) {

        this.planetService = planetService;
        this.planetResourceService = planetResourceService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getRemoteUser()!=null){
            PlanetServiceModel currentPlanet = planetService.getCurrentPlanet(request.getRemoteUser());
            PlanetResourceModelInfo current = planetResourceService.findById(currentPlanet.getId());
            current.setDescription(currentPlanet.getDescription()).setImgUrl(currentPlanet.getImgUrl()).setName(currentPlanet.getName());
            request.getSession().setAttribute("planetModelInfo", current);
        }
        filterChain.doFilter(request, servletResponse);
    }
}

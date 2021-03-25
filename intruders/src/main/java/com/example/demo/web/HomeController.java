package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.models.serviceModels.PlanetServiceModel;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.PlanetService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final PlanetModelInfo planetModelInfo;
    private final PlanetService planetService;
    private final PlanetResourceService planetResourceService;

    public HomeController(PlanetModelInfo planetModelInfo, PlanetService planetService, PlanetResourceService planetResourceService) {

        this.planetModelInfo = planetModelInfo;
        this.planetService = planetService;
        this.planetResourceService = planetResourceService;
    }

    @GetMapping("/")
    public String welcome(Authentication authentication, Model model) {
        if (authentication == null) {
            return "index";
        }
        PlanetServiceModel currentPlanet = planetService.getCurrentPlanet(authentication.getName());
        planetResourceService.findById(currentPlanet.getId());
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "home";

    }


}

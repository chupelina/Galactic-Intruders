package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import com.example.demo.services.PlanetService;
import com.example.demo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PlanetService planetService;
    private final UserService userService;
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;

    public HomeController(PlanetService planetService, UserService userService, OwnMaterialsServiceModel ownMaterialsServiceModel) {
        this.planetService = planetService;
        this.userService = userService;
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
    }

    @GetMapping("/")
    public String welcome(Authentication authentication, Model model) {
        if (authentication == null) {
            return "index";
        }
        model.addAttribute("planetInfoModel",
                planetService.getCurrentPlanet(userService.findByUsername(authentication.getName()).getPlanet()));
        model.addAttribute("ownMaterialsServiceModel", ownMaterialsServiceModel);
        return "home";

    }


}

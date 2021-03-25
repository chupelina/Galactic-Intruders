package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetModelInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMeController {
    private final PlanetModelInfo planetModelInfo;

    public AboutMeController(PlanetModelInfo planetModelInfo) {

        this.planetModelInfo = planetModelInfo;
    }

    @GetMapping("/aboutMe")
    public String about(Model model){
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "aboutMe";
    }

}

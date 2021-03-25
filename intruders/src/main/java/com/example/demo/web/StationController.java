package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetModelInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StationController {
    private final PlanetModelInfo planetModelInfo;

    public StationController(PlanetModelInfo planetModelInfo) {
        this.planetModelInfo = planetModelInfo;
    }

    @GetMapping("/stations")
    public String about(Model model){
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "stations";
    }
}

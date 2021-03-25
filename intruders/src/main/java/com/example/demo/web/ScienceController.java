package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.services.ScienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ScienceController {
    private final PlanetModelInfo planetModelInfo;
    private final ScienceService scienceService;

    public ScienceController(PlanetModelInfo planetModelInfo, ScienceService scienceService) {

        this.planetModelInfo = planetModelInfo;
        this.scienceService = scienceService;
    }

    @GetMapping("/science")
    public String getAllScience(Model model){
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "science";
    }

    // TODO: 21.3.2021 not working 
    @GetMapping("/science/{id}")
    public String levelUp(@PathVariable long id){
        System.out.println("OKOKOKOKOKOK");
        System.out.println(id);
        return "redirect:/science";
    }
}

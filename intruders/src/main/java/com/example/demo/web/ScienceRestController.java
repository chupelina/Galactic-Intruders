package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.models.viewModels.ScienceViewModel;
import com.example.demo.services.ScienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class ScienceRestController {
    private final ScienceService scienceService;
     private final PlanetModelInfo planetModelInfo;

    public ScienceRestController(ScienceService scienceService, PlanetModelInfo planetModelInfo) {
        this.scienceService = scienceService;
        this.planetModelInfo = planetModelInfo;
    }

    @GetMapping("/api/science")
    public List<ScienceViewModel> loadAll(Model model) {
       return scienceService.getAllScienceProjectsByCurrentPlanet(planetModelInfo.getId());
    }

    @PostMapping("/api/science/{id}")
    public String getCurrent(@PathVariable Long id) {
        scienceService.updateScienceLevel(id);
        return "redirect:/science";
    }


}

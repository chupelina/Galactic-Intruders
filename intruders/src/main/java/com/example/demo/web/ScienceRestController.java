package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import com.example.demo.models.viewModels.ScienceViewModel;
import com.example.demo.services.ScienceService;
import com.example.demo.services.UserService;
import com.google.gson.Gson;
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
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;
    private final UserService userService;
    private final Gson gson;

    public ScienceRestController(ScienceService scienceService, OwnMaterialsServiceModel ownMaterialsServiceModel, UserService userService, Gson gson) {
        this.scienceService = scienceService;
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
        this.userService = userService;
        this.gson = gson;
    }

    @GetMapping("/science/api")
    public String loadAll(Model model) {
        model.addAttribute("ownMaterialsServiceModel", ownMaterialsServiceModel);
        List<ScienceViewModel> allScienceProjectsByCurrentPlanet = scienceService.getAllScienceProjectsByCurrentPlanet(userService.findById(ownMaterialsServiceModel.getPlanetId()));
        return gson.toJson(allScienceProjectsByCurrentPlanet);
    }

    @PostMapping("/science/api/{id}")
    public String getCurrent(@PathVariable Long id) {
        scienceService.updateScienceLevel(id);
        return "redirect:/science";
    }


}

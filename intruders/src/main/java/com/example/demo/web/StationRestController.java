package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import com.example.demo.models.viewModels.StationViewModel;
import com.example.demo.services.StationService;
import com.example.demo.services.UserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
public class StationRestController {
    private final StationService stationService;
    private final Gson gson;
    private final UserService userService;
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;

    public StationRestController(StationService stationService, Gson gson, UserService userService, OwnMaterialsServiceModel ownMaterialsServiceModel) {
        this.stationService = stationService;
        this.gson = gson;
        this.userService = userService;
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
    }

    @GetMapping("/stations/api")
    public String returnAllStations() {
        List<StationViewModel> allStations = stationService.getAllStationsByCurrentPlanet(userService.findById(ownMaterialsServiceModel.getPlanetId()));
        return gson.toJson(allStations);
    }

    @PostMapping("/stations/api/{id}")
    public String getCurrent(@PathVariable Long id) {
        stationService.updateScienceLevel(id);
        return "redirect:/science";
    }
}

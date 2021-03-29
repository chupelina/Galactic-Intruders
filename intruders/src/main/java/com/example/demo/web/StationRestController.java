package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.StationViewModel;
import com.example.demo.services.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Controller
public class StationRestController {
    private final StationService stationService;

    public StationRestController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/api/stations")
    public List<StationViewModel> returnAllStations(HttpSession session) {
        PlanetResourceModelInfo planetModelInfo = (PlanetResourceModelInfo)session.getAttribute("planetModelInfo");

       return stationService.getAllStationsByCurrentPlanet(planetModelInfo.getId());
    }

    @PostMapping("/api/stations/{id}")
    public String getCurrent(@PathVariable Long id) {
        stationService.updateScienceLevel(id);
        return "redirect:/science";
    }
}

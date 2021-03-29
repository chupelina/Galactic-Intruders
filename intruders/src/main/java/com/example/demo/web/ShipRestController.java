package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.ShipViewModel;
import com.example.demo.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RestController
public class ShipRestController {
    private final ShipService shipService;

    public ShipRestController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/api/ships")
    public List<ShipViewModel> about(Model model, HttpSession session){

        PlanetResourceModelInfo planetModelInfo = (PlanetResourceModelInfo)session.getAttribute("planetModelInfo");
        model.addAttribute("planetModelInfo", planetModelInfo);
       return  shipService.getAllScienceProjectsByCurrentPlanet(planetModelInfo.getId());
    }
    @PostMapping("/api/ships/{id}/{count}")
    public String  getCurrent(@PathVariable Long id, @PathVariable int count){
        shipService.addShips(id, count);
        return "redirect:/";

    }
}

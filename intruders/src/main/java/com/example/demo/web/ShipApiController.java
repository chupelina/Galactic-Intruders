package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import com.example.demo.models.viewModels.ShipViewModel;
import com.example.demo.services.ShipService;
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
public class ShipApiController {
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;
    private final ShipService shipService;
    private final UserService userService;
    private final Gson gson;

    public ShipApiController(OwnMaterialsServiceModel ownMaterialsServiceModel, ShipService shipService, UserService userService, Gson gson) {
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
        this.shipService = shipService;
        this.userService = userService;
        this.gson = gson;
    }

    @GetMapping("/ships/api")
    public String about(Model model){
        model.addAttribute("ownMaterialsServiceModel",ownMaterialsServiceModel);
        List<ShipViewModel> allArmyByCurrentPlanet = shipService.getAllScienceProjectsByCurrentPlanet(userService.findById(ownMaterialsServiceModel.getPlanetId()));
        return gson.toJson(allArmyByCurrentPlanet);
    }
    @PostMapping("/ships/api/{id}/{count}")
    public String  getCurrent(@PathVariable Long id, @PathVariable int count){
        shipService.addShips(id, count);
        return "redirect:/";

    }
}

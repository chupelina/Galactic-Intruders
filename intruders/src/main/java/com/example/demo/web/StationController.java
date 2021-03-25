package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StationController {
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;

    public StationController(OwnMaterialsServiceModel ownMaterialsServiceModel) {
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
    }

    @GetMapping("/stations")
    public String about(Model model){
        model.addAttribute("ownMaterialsServiceModel",ownMaterialsServiceModel);
        return "stations";
    }
}

package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShipController {
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;

    public ShipController(OwnMaterialsServiceModel ownMaterialsServiceModel) {
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
    }

    @GetMapping("/ships")
    public String about(Model model){
        model.addAttribute("ownMaterialsServiceModel",ownMaterialsServiceModel);
        return "ships";
    }
}

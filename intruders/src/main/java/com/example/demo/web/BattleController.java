package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BattleController {
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;

    public BattleController(OwnMaterialsServiceModel ownMaterialsServiceModel) {
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
    }

    @GetMapping("/battle")
    public String battle(Model model){
        model.addAttribute("ownMaterialsServiceModel",ownMaterialsServiceModel);
        return "battle";
    }
}

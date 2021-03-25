package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetModelInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BattleController {
    private final PlanetModelInfo planetModelInfo;

    public BattleController(PlanetModelInfo planetModelInfo) {

        this.planetModelInfo = planetModelInfo;
    }

    @GetMapping("/battle")
    public String battle(Model model){
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "battle";
    }
}

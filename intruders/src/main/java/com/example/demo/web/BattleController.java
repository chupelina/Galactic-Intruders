package com.example.demo.web;

import com.example.demo.models.bindingModels.ArmyBingingResult;
import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.ShipModel;
import com.example.demo.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BattleController {
    private final ShipService shipService;

    public BattleController(ShipService shipService) {
        this.shipService = shipService;
    }


    @GetMapping("/battle")
    public String battle(Model model, HttpSession session){
        if(!model.containsAttribute("armyBingingResult")){
            model.addAttribute("armyBingingResult", new ShipModel());
        }
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        List<ShipModel> army = shipService.getArmy(
                (PlanetResourceModelInfo) session.getAttribute("planetModelInfo"));
        System.out.println(army);
        model.addAttribute("ships", army);
        return "battle";
    }

    @PostMapping("/battle")
    public  String startBattle(@Valid ShipModel shipModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println();
        return "redirect:/battle";
    }
}

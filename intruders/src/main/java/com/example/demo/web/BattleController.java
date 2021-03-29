package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BattleController {


    @GetMapping("/battle")
    public String battle(Model model, HttpSession session){
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        return "battle";
    }
}

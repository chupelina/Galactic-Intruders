package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.services.ScienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ScienceController {


    @GetMapping("/science")
    public String getAllScience(Model model, HttpSession session){
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        return "science";
    }

}

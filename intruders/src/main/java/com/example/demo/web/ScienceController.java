package com.example.demo.web;

import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScienceController {
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;

    public ScienceController(OwnMaterialsServiceModel ownMaterialsServiceModel) {
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
    }

    @GetMapping("/science")
    public String getAllScience(Model model){
        model.addAttribute("ownMaterialsServiceModel",ownMaterialsServiceModel);
        return "science";
    }

    // TODO: 21.3.2021 not working 
    @GetMapping("/science/{id}")
    public String levelUp(@PathVariable long id){
        System.out.println("OKOKOKOKOKOK");
        System.out.println(id);
        return "redirect:/science";
    }
}

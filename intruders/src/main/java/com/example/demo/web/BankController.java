package com.example.demo.web;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.PlanetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class BankController {
    private final PlanetResourceService planetResourceService;
    private final PlanetService planetService;
    private final PlanetModelInfo planetModelInfo;

    public BankController(PlanetResourceService planetResourceService, PlanetService planetService, PlanetModelInfo planetModelInfo) {
        this.planetResourceService = planetResourceService;
        this.planetService = planetService;
        this.planetModelInfo = planetModelInfo;
    }

    @GetMapping("/bank")
    public String bank(Model model){
        if(!model.containsAttribute("bankBindingModel")){
            model.addAttribute("bankBindingModel", new BankBindingModel());
            model.addAttribute("positive", false);
            model.addAttribute("notResource", false);
        }
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "bank";
    }

    @PostMapping("/bank")
    public String bankChanger(@Valid @ModelAttribute BankBindingModel bankBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Principal principal){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("positive", true);
            redirectAttributes.addFlashAttribute("bankBindingModel", bankBindingModel);
            return "redirect:/bank";
        }
       boolean isOK =planetResourceService.changeMaterials(bankBindingModel, planetService.getCurrentPlanet(principal.getName()));
        if(isOK){
            return "redirect:/";
        }else{
            redirectAttributes.addFlashAttribute("notResource", true);
            redirectAttributes.addFlashAttribute("bankBindingModel", bankBindingModel);
            return "redirect:/bank";
        }

    }

}

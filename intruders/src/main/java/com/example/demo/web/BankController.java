package com.example.demo.web;

import com.example.demo.models.bindingModels.BankBindingModel;
import com.example.demo.models.serviceModels.OwnMaterialsServiceModel;
import com.example.demo.services.PlanetResourceService;
import com.example.demo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BankController {
    private final OwnMaterialsServiceModel ownMaterialsServiceModel;
    private final PlanetResourceService planetResourceService;
    private final UserService userService;

    public BankController(OwnMaterialsServiceModel ownMaterialsServiceModel, PlanetResourceService planetResourceService, UserService userService) {
        this.ownMaterialsServiceModel = ownMaterialsServiceModel;
        this.planetResourceService = planetResourceService;
        this.userService = userService;
    }

    @GetMapping("/bank")
    public String bank(Model model){
        if(!model.containsAttribute("bankBindingModel")){
            model.addAttribute("bankBindingModel", new BankBindingModel());
            model.addAttribute("positive", false);
            model.addAttribute("notResource", false);
        }
        model.addAttribute("ownMaterialsServiceModel",ownMaterialsServiceModel);

        return "bank";
    }

    @PostMapping("/bank")
    public String bankChanger(@Valid @ModelAttribute BankBindingModel bankBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Authentication authentication){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("positive", true);
            redirectAttributes.addFlashAttribute("bankBindingModel", bankBindingModel);
            return "redirect:/bank";
        }
       boolean isOK =planetResourceService.changeMaterials(bankBindingModel, userService.findByUsername(authentication.getName())
               .getPlanet().getPlanetResourceEntity());
        if(isOK){
            return "redirect:/";
        }else{
            redirectAttributes.addFlashAttribute("notResource", true);
            redirectAttributes.addFlashAttribute("bankBindingModel", bankBindingModel);
            return "redirect:/bank";
        }

    }

}

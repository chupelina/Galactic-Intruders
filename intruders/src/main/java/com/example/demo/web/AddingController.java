package com.example.demo.web;

import com.example.demo.models.bindingModels.AddingBindingModel;
import com.example.demo.models.entities.enums.TypeOfMadeEnum;
import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.services.ScienceService;
import com.example.demo.services.ShipService;
import com.example.demo.services.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AddingController {
    private final ShipService shipService;
    private final StationService stationService;
    private final ScienceService scienceService;
    private final PlanetModelInfo planetModelInfo;


    public AddingController(ShipService shipService, StationService stationService, ScienceService scienceService, PlanetModelInfo planetModelInfo) {
        this.shipService = shipService;
        this.stationService = stationService;
        this.scienceService = scienceService;

        this.planetModelInfo = planetModelInfo;
    }

    @GetMapping("/adding")
    public String addNewModel(Model model){
        if(!model.containsAttribute("addingBindingModel")){
            model.addAttribute("addingBindingModel", new AddingBindingModel());
            model.addAttribute("withSameName", false);
        }
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "adding";
    }

    @PostMapping("/adding")
    public String addNewModelConfirm(@Valid AddingBindingModel addingBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addingBindingModel", addingBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addingBindingModel",
                    bindingResult);
            return "redirect:/adding";
        }
        boolean isOk = false;
       if(addingBindingModel.getType().toUpperCase().equals(TypeOfMadeEnum.SHIP.name())){
         isOk = shipService.createNewShip(addingBindingModel);
       }else if(addingBindingModel.getType().toUpperCase().equals(TypeOfMadeEnum.SCIENCE.name())){
           isOk = scienceService.createNewScience(addingBindingModel);
       }else if(addingBindingModel.getType().toUpperCase().equals(TypeOfMadeEnum.STATION.name())){
          isOk= stationService.createNewStation(addingBindingModel);
       }
       if(isOk){
           return "redirect:/";
       }
      redirectAttributes.addFlashAttribute("addingBindingModel", addingBindingModel);
       redirectAttributes.addFlashAttribute("withSameName", true);
        return "redirect:/adding";
    }
}

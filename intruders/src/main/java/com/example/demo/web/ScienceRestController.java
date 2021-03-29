package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.viewModels.ScienceViewModel;
import com.example.demo.services.ScienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RestController
public class ScienceRestController {
    private final ScienceService scienceService;

    public ScienceRestController(ScienceService scienceService){
        this.scienceService = scienceService;
 ;
    }

    @GetMapping("/api/science")
    public List<ScienceViewModel> loadAll(Model model, HttpSession session) {
        PlanetResourceModelInfo planetModelInfo = (PlanetResourceModelInfo)session.getAttribute("planetModelInfo");
        model.addAttribute("planetModelInfo", planetModelInfo);

       return scienceService.getAllScienceProjectsByCurrentPlanet(planetModelInfo.getId());
    }

    @PostMapping("/api/science/{id}")
    public String getCurrent(@PathVariable Long id) {
        scienceService.updateScienceLevel(id);
        return "redirect:/science";
    }


}

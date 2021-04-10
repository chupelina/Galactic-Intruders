package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.models.serviceModels.ShipModel;
import com.example.demo.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private final ShipService shipService;

    public HomeController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String welcome() {
        return "index";

    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        List<ShipModel> army = shipService.getArmy(
                (PlanetResourceModelInfo) session.getAttribute("planetModelInfo"));
                System.out.println(army);
        model.addAttribute("ships", army);

        return "home";
    }


}

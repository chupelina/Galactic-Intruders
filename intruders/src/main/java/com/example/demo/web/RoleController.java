package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetModelInfo;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {

    private final UserService userService;
    private final PlanetModelInfo planetModelInfo;

    public RoleController(UserService userService, PlanetModelInfo planetModelInfo) {
        this.userService = userService;
        this.planetModelInfo = planetModelInfo;
    }

    @GetMapping("/changeRole")
    public String about(Model model){
        model.addAttribute("usernames", userService.getAllUsers());
        model.addAttribute("planetModelInfo", planetModelInfo);
        return "changeRole";
    }

    @PostMapping("/changeRole")
    public String addConfirm(@RequestParam String username, @RequestParam
            String role){
        userService.changeUserRole(username, role);
        return "redirect:/home";
    }
}

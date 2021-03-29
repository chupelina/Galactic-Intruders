package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RoleController {

    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changeRole")
    public String about(Model model, HttpSession session){
        model.addAttribute("usernames", userService.getAllUsers());
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        return "changeRole";
    }

    @PostMapping("/changeRole")
    public String addConfirm(@RequestParam String username, @RequestParam
            String role){
        userService.changeUserRole(username, role);
        return "redirect:/home";
    }
}

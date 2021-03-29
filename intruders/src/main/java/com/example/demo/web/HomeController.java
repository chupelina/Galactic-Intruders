package com.example.demo.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome(Authentication authentication, Model model, HttpSession session) {
        if (authentication == null) {
            return "index";
        }
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        return "home";

    }


}

package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "index";

    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        return "home";
    }


}

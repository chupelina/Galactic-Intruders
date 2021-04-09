package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StationController {


    @GetMapping("/stations")
    public String about(Model model, HttpSession session){
        model.addAttribute("planetModelInfo", session.getAttribute("planetModelInfo"));
        return "stations";
    }
}

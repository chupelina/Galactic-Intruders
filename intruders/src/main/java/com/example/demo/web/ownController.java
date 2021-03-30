package com.example.demo.web;

import com.example.demo.models.serviceModels.PlanetResourceModelInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ownController {

    @GetMapping("/owns")
    public PlanetResourceModelInfo getInfo(HttpSession session){
        return (PlanetResourceModelInfo) session.getAttribute("planetModelInfo");
    }
}

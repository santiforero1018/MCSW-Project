package edu.eci.mcsw.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @GetMapping("/")
    public String home() {
        return "homePage";
    }
}

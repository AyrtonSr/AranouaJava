package edu.aranoua.aula_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String init(){
        System.out.println("Testando HomeController - Console");
        return "Testando HomeController - Console";
    }
}

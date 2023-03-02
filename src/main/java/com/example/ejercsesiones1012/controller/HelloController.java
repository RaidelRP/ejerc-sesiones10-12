package com.example.ejercsesiones1012.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.usuario}")
    String usuario;
    @GetMapping("/hola")
    public String saludar(){
        System.out.println(usuario);
        return "Hola, mi gente!";
    }
}

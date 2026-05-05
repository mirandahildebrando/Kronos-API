package com.Kronos.Kronos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect: https://kronos-api-ck9x.onrender.com/swagger-ui/index.html";
    }
}

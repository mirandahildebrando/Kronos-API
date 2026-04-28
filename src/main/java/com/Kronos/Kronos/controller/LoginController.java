package com.Kronos.Kronos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.Kronos.Kronos.dtos.LoginRequestDTO;
import com.Kronos.Kronos.service.LoginService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
    String response = loginService.login(dto);
    return ResponseEntity.ok(response);
    }

}
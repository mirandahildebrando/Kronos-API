package com.Kronos.Kronos.Login.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Kronos.Kronos.Login.dtos.LoginRequestDTO;
import com.Kronos.Kronos.Login.model.Login;
import com.Kronos.Kronos.Login.repository.LoginRepository;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String login(LoginRequestDTO dto) {

        Login login = loginRepository.findByUsername(dto.username())
            .orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
            );

        if (!login.getPassword().equals(dto.password())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        return "Login realizado com sucesso!";
    }
}
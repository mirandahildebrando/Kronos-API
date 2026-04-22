package com.Kronos.Kronos.Login.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Kronos.Kronos.Login.dtos.LoginRequestDTO;
import com.Kronos.Kronos.Login.model.Login;
import com.Kronos.Kronos.Login.repository.LoginRepository;
import com.Kronos.Kronos.Register.repository.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(LoginRequestDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername())
            .orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
            );

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        return "Login realizado com sucesso!";
    }


}
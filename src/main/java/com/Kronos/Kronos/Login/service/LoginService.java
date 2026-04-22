package com.Kronos.Kronos.Login.service;

import org.springframework.stereotype.Service;

import com.Kronos.Kronos.Login.dtos.LoginRequestDTO;
import com.Kronos.Kronos.Register.model.User;
import com.Kronos.Kronos.Register.repository.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(LoginRequestDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return "Login realizado com sucesso!";
    }
}
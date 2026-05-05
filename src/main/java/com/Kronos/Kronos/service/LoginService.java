package com.Kronos.Kronos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Kronos.Kronos.dtos.LoginRequestDTO;
import com.Kronos.Kronos.entity.User;
import com.Kronos.Kronos.repository.UserRepository;



@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String login(LoginRequestDTO dto) {

        System.out.println("LOGIN TENTANDO: " + dto.getUsername());
        System.out.println("LOGIN TENTANDO: " + dto.getPassword());

        User user = userRepository.findByUsername(dto.getUsername())
            .orElseThrow(() -> {
                return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado");
            });

        System.out.println("SENHA NO BANCO: " + user.getPassword());

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        return "Login realizado com sucesso!";
    }

}
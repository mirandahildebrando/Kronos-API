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

    System.out.println("LOGIN TENTANDO: " + dto.getUsername());
    System.out.println("SENHA DIGITADA: " + dto.getPassword());

    User user = userRepository.findByUsername(dto.getUsername())
        .orElseThrow(() -> {
            System.out.println("USUARIO NAO ENCONTRADO");
            return new RuntimeException("Usuário não encontrado");
        });

    System.out.println("SENHA NO BANCO: " + user.getPassword());

    if (!user.getPassword().equals(dto.getPassword())) {
        System.out.println("SENHA NAO CONFERE");
        throw new RuntimeException("Senha inválida");
    }

    System.out.println("LOGIN OK");

    return "Login realizado com sucesso!";
}
}
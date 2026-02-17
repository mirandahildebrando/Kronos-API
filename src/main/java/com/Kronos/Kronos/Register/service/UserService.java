package com.Kronos.Kronos.Register.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Kronos.Kronos.Register.dtos.UserDTO;
import com.Kronos.Kronos.Register.model.User;
import com.Kronos.Kronos.Register.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());

        User savedUser = userRepository.save(user);
  

        UserDTO responseDto = new UserDTO(
                savedUser.getUsername(),
                "*********"
        );
        return responseDto;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        userRepository.save(user);
        return dto;
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        } else {
            userRepository.deleteById(id);
    }
}

    }

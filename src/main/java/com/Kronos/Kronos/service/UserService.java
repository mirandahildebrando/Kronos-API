package com.Kronos.Kronos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Kronos.Kronos.dtos.UserDTO;
import com.Kronos.Kronos.entity.User;
import com.Kronos.Kronos.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        User savedUser = userRepository.save(user);

        return new UserDTO(
                savedUser.getUsername(),
                "*********"
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        userRepository.save(user);

        return dto;
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
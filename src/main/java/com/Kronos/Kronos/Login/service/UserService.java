package com.Kronos.Kronos.Login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Kronos.Kronos.Login.dtos.UserDTO;
import com.Kronos.Kronos.Login.model.User;
import com.Kronos.Kronos.Login.repository.UserRepository;

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
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        userRepository.save(user);
        return dto;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

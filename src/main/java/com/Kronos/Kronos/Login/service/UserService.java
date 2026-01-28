package com.Kronos.Kronos.Login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Kronos.Kronos.Login.dtos.UserRequestDTO;
import com.Kronos.Kronos.Login.model.User;
import com.Kronos.Kronos.Login.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserRequestDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        User savedUser = userRepository.save(user);
  

        UserRequestDTO responseDto = new UserRequestDTO();
        responseDto.setUsername(savedUser.getUsername());
        responseDto.setPassword("*********");
        return responseDto;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<Object> updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
        .map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

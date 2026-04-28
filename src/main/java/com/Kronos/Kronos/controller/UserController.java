package com.Kronos.Kronos.controller;

import com.Kronos.Kronos.dtos.UserDTO;
import com.Kronos.Kronos.entity.User;
import com.Kronos.Kronos.repository.UserRepository;
import com.Kronos.Kronos.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        UserDTO createdUser = userService.createUser(dto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/byId")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO dto) {

        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/reset")
    public ResponseEntity<String> resetUsers() {
        userRepository.deleteAll();
        return ResponseEntity.ok("Todos os usuários foram deletados.");
    }
}
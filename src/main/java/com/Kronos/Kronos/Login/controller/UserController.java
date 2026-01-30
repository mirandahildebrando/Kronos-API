package com.Kronos.Kronos.Login.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Kronos.Kronos.Login.dtos.UserRequestDTO;
import com.Kronos.Kronos.Login.model.User;
import com.Kronos.Kronos.Login.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserRequestDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        UserRequestDTO createdUser = userService.createUser(dto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    }

    @PutMapping("/{id}")
        public ResponseEntity<UserRequestDTO> updateUser(
        @PathVariable Long id,
        @RequestBody UserRequestDTO dto) {

       UserRequestDTO updatedUser = userService.updateUser(id, dto);
       return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

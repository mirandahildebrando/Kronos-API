package com.Kronos.Kronos.controller;

import com.Kronos.Kronos.dtos.APIResponse;
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
    public ResponseEntity<APIResponse<UserDTO>> createUser(@RequestBody UserDTO dto) {
        UserDTO createdUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(createdUser, "Usuário criado com sucesso"));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(new APIResponse<>(userService.getAllUsers(), "Lista de usuários retornada com sucesso"));
    }

    @GetMapping("/byId")
    public ResponseEntity<APIResponse<User>> getUserById(@RequestParam Long id) {
        return ResponseEntity.ok(new APIResponse<>(userService.getUserById(id), "Usuário retornado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UserDTO>> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO dto) {

        return ResponseEntity.ok(new APIResponse<>(userService.updateUser(id, dto), "Usuário atualizado com sucesso"));
    }

    @DeleteMapping("/reset")
    public ResponseEntity<APIResponse<String>> resetUsers() {
        userRepository.deleteAll();
        return ResponseEntity.ok(new APIResponse<>(null, "Todos os usuários foram deletados."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new APIResponse<>(null, "Usuário deletado com sucesso"));
    }
}
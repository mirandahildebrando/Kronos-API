package com.Kronos.Kronos.Register.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
    String username, 
    String password
) {}
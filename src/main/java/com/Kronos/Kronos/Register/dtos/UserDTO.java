package com.Kronos.Kronos.Register.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
    @NotBlank 
    @Size(min = 5, max = 30) 
    String username,

    @NotBlank 
    @Size(min = 8, max = 20) 
    String password
) {}
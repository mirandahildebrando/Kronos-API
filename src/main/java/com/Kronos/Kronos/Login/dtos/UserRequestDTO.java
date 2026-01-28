package com.Kronos.Kronos.Login.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank
    @Size(min = 5, max = 30)
    private String username;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

}

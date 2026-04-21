package com.Kronos.Kronos.Login.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;

    @JsonCreator
    public LoginRequestDTO(
        @JsonProperty("username") String username,
        @JsonProperty("password") String password
    ) {
        this.username = username;
        this.password = password;
    }
}
package com.Kronos.Kronos.Login.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequestDTO(
    @JsonProperty("username") String username,
    @JsonProperty("password") String password
)
{ 
        @JsonCreator
        public LoginRequestDTO {}
}
package com.example.core.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty
    private String mobile;

    @NotEmpty
    private String password;

}

package com.benriya.platform.benriya_backend.dto;

import com.benriya.platform.benriya_backend.enums.RolesEnum;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RolesEnum role;
}
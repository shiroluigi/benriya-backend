package com.benriya.platform.benriya_backend.dto;


import java.util.UUID;

import com.benriya.platform.benriya_backend.enums.RolesEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private RolesEnum role;
}
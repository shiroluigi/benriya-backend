package com.benriya.platform.benriya_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private UserResponse user;
}
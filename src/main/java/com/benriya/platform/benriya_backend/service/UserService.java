package com.benriya.platform.benriya_backend.service;

import java.util.UUID;

import com.benriya.platform.benriya_backend.dto.RegisterRequest;
import com.benriya.platform.benriya_backend.models.User;

public interface UserService {

    User registerUser(RegisterRequest request);

    User loginUser(String email, String password);

    User getUserById(UUID uuid);
}
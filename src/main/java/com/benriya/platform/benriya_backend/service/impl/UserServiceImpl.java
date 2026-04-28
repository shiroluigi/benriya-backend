package com.benriya.platform.benriya_backend.service.impl;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.benriya.platform.benriya_backend.dto.RegisterRequest;
import com.benriya.platform.benriya_backend.enums.RolesEnum;
import com.benriya.platform.benriya_backend.models.ProviderProfile;
import com.benriya.platform.benriya_backend.models.User;
import com.benriya.platform.benriya_backend.repository.UserRepository;
import com.benriya.platform.benriya_backend.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        if (request.getRole() == null) {
            throw new RuntimeException("Role is required");
        }
        if (request.getRole() == RolesEnum.ROLE_ADMIN) {
            throw new RuntimeException("Cannot assign admin role");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        user.setActive(true);
        user.setEmailVerified(false);

        if (request.getRole() == RolesEnum.ROLE_PROVIDER) {

            ProviderProfile profile = new ProviderProfile();
            profile.setUser(user);
            profile.setRating(0.0);
            profile.setTotalJobs(0);

            user.setProviderProfile(profile);
        }

        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        return user;
    }

    @Override
    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
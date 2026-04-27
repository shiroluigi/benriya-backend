package com.benriya.platform.benriya_backend.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.benriya.platform.benriya_backend.enums.RolesEnum;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_email", columnList = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String firstName;
    private String lastName;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolesEnum role;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private boolean isEmailVerified = false;

    private String phoneNumber;

    private String profileImageUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    //Provider profile mapping
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ProviderProfile providerProfile;
}
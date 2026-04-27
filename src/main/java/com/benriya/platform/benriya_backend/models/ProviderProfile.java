package com.benriya.platform.benriya_backend.models;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "provider_profiles")
public class ProviderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 500)
    private String bio;

    private String skills;

    private Double rating = 0.0;

    private Integer totalJobs = 0;

    private String location;

    private Double hourlyRate;
}
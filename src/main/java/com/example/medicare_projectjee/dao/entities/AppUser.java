package com.example.medicare_projectjee.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {
    @Id
    @Column(length = 50)
    private String username;
    private String password;
    private String role; // ADMIN, MEDECIN, PATIENT
}
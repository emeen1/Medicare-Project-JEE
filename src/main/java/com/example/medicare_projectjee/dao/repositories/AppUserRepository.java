package com.example.medicare_projectjee.dao.repositories;


import com.example.medicare_projectjee.dao.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}

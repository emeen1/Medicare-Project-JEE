package com.example.medicare_projectjee.dao.repositories;

import com.example.medicare_projectjee.dao.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByUsername(String username);
}

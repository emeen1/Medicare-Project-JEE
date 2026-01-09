package com.example.medicare_projectjee.dao.repositories;

import com.example.medicare_projectjee.dao.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername(String username);
}
